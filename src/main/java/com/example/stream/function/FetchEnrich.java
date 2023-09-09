package com.example.stream.function;

import com.example.stream.FakeApi;
import com.example.stream.FakeRepository;
import com.example.stream.model.EnrichEnd;
import com.example.stream.model.EnrichStart;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class FetchEnrich implements Consumer<EnrichStart> {

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
            bridge.send("enrichEnd", enrichEnd);
        } else {
            bridge.send("enrichStart", enrichStart);
        }
    }

}
