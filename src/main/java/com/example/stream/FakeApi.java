package com.example.stream;

import com.example.stream.model.Ro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FakeApi {

    public void fetch(Ro ro) {
        log.info("Fetching {} for {}", ro.getType(), ro.getId());
    }
}
