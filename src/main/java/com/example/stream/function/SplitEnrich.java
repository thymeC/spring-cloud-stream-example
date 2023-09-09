package com.example.stream.function;

import com.example.stream.FakeRepository;
import com.example.stream.model.EnrichStart;
import com.example.stream.model.PullRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class SplitEnrich implements Consumer<PullRequest> {

    private final StreamBridge bridge;
    private final FakeRepository repository;
    private final List<String> enrichments = List.of("JIRA", "BUILD", "SONAR");


    @Override
    public void accept(PullRequest pullRequest) {
        repository.findPullRequest(pullRequest);
        for (var enrichment : enrichments) {
            var ro = new EnrichStart();
            ro.setType(enrichment);
            ro.setId(pullRequest.getId());
            ro.generateKey();
            bridge.send("roTask", ro);
        }
    }
}
