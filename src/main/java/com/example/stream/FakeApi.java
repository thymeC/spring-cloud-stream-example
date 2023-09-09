package com.example.stream;

import com.example.stream.model.EnrichStart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FakeApi {

    public void fetch(EnrichStart enrichStart) {
        log.info("Fetching {} for {}", enrichStart.getType(), enrichStart.getId());
    }
}
