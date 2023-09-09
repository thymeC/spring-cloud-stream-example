package com.example.stream;

import com.example.stream.model.EnrichEnd;
import com.example.stream.model.PullRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FakeRepository {

    public void findPullRequest(PullRequest pullRequest) {
        log.info("found pullRequest {}", pullRequest.getKey());
    }

    public void findEnrichEnd(EnrichEnd enrichEnd) {
        log.info("found enrichEnd {}", enrichEnd.getKey());
    }

    public void save(EnrichEnd enrichEnd) {
        log.info("{} has been saved", enrichEnd.getKey());
    }
}
