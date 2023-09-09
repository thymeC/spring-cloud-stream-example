package com.example.stream.function;

import com.example.stream.FakeApi;
import com.example.stream.FakeRepository;
import com.example.stream.model.EnrichEnd;
import com.example.stream.model.EnrichStart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchEnrich implements Consumer<EnrichStart> {

    public static final String BINDING_NAME = "fetchEnrich-in-0";

    private final StreamBridge bridge;
    private final FakeRepository repository;
    private final FakeApi api;

    @Override
    public void accept(EnrichStart enrichStart) {
        var random = new Random();
        api.fetch(enrichStart);

        if (random.nextInt(10) < random.nextInt(15)) {
            var enrichEnd = new EnrichEnd();
            enrichEnd.setId(enrichStart.getId());
            enrichEnd.setType(enrichStart.getType());
            enrichEnd.generateKey();
            repository.save(enrichEnd);
            bridge.send(RunRule.BINDING_NAME, enrichEnd);
        } else {
            log.info("Enrichment {} for {} failed", enrichStart.getType(), enrichStart.getId());
            bridge.send(FetchEnrich.BINDING_NAME, enrichStart);
        }
    }

}
