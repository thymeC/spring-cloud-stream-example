package com.example.stream.function;

import com.example.stream.model.Fus;
import com.example.stream.model.Ro;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class FusRouter implements Consumer<Fus> {

    private final StreamBridge bridge;
    private final List<String> enrichments = List.of("JIRA", "BUILD", "SONAR");


    @Override
    public void accept(Fus fus) {
        for (var enrichment : enrichments) {
            var ro = new Ro();
            ro.setType(enrichment);
            ro.setId(fus.getId());
            ro.generateKey();
            bridge.send("roTask", ro);
        }
    }
}
