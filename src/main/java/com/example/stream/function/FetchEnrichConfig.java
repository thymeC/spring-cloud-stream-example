package com.example.stream.function;

import com.example.stream.FakeApi;
import com.example.stream.FakeRepository;
import com.example.stream.model.AbstractMessage;
import com.example.stream.model.EnrichEnd;
import com.example.stream.model.EnrichStart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchEnrichConfig {

    private final FakeRepository repository;
    private final ObjectMapper mapper;
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

    @Bean
    public Function<KStream<String, EnrichStart>, KStream<String, String>[]> fetchEnrich() {
        return input -> {
            var map = input
                    .split()
                    .branch((key, value) -> randomPass(), Branched.as("success"))
                    .defaultBranch(Branched.as("failure"));

            var keys = List.copyOf(map.keySet());

            var success = map.get(keys.get(1)).map((k, v) -> {
                api.fetch(v);
                var end = convert(v);
                repository.save(end);
                log.info("Enrichment {} for {} succeeded", v.getType(), v.getId());
                try {
                    return new KeyValue<>(end.getKey(), mapper.writeValueAsString(end));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

            var failed = map.get(keys.get(0)).map((k, v) -> {
                log.info("Enrichment {} for {} failed", v.getType(), v.getId());
                try {
                    return new KeyValue<>(v.getKey(), mapper.writeValueAsString(v));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

            return new KStream[]{success, failed};
        };
    }

}
