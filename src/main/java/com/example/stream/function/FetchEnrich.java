package com.example.stream.function;

import com.example.stream.FakeApi;
import com.example.stream.FakeRepository;
import com.example.stream.model.AbstractMessage;
import com.example.stream.model.EnrichEnd;
import com.example.stream.model.EnrichStart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchEnrich implements Function<KStream<String, EnrichStart>, KStream<String, ? extends AbstractMessage>[]> {

    private final FakeRepository repository;
    private final FakeApi api;

    private boolean randomPass() {
        var random = new Random();
        return random.nextInt(10) < random.nextInt(15);
    }

    private EnrichEnd convert(EnrichStart start) {
        var enrichEnd = new EnrichEnd();
        enrichEnd.setId(start.getId());
        enrichEnd.setType(start.getType());
        enrichEnd.generateKey();
        return enrichEnd;
    }

    @Override
    public KStream<String, ? extends AbstractMessage>[] apply(KStream<String, EnrichStart> input) {
        var map = input.split()
                .branch((key, value) -> randomPass(), Branched.as("success"))
                .defaultBranch(Branched.as("failure"));

        var success = map.get("success").map((k, v) -> {
            api.fetch(v);
            var end = convert(v);
            repository.save(end);
            return new KeyValue<>(end.getKey(), end);
        });

        var failed = map.get("failure").map((k, v) -> {
            log.info("Enrichment {} for {} failed", v.getType(), v.getId());
            return new KeyValue<>(v.getKey(), v);
        });

        return new KStream[]{success, failed};
    }

}
