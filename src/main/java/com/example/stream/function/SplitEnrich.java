package com.example.stream.function;

import com.example.stream.FakeRepository;
import com.example.stream.model.EnrichStart;
import com.example.stream.model.PullRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SplitEnrich implements Function<KStream<String, PullRequest>, KStream<String, EnrichStart>> {

    private final FakeRepository repository;
    private final List<String> enrichments = List.of("JIRA", "BUILD", "SONAR");

    private Stream<EnrichStart> convert(PullRequest value) {
        return enrichments.stream().map(
                enrichments -> {
                    var start = new EnrichStart();
                    start.setType(enrichments);
                    start.setId(value.getId());
                    start.generateKey();
                    return start;
                }
        );
    }

    @Override
    public KStream<String, EnrichStart> apply(KStream<String, PullRequest> input) {
        var next = input.flatMap((key, value) -> {
            repository.findPullRequest(value);
            return convert(value)
                    .map(enrichStart -> new KeyValue<>(enrichStart.getKey(), enrichStart))
                    .toList();
        });
        return next;
    }

}
