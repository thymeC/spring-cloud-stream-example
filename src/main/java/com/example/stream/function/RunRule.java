package com.example.stream.function;

import com.example.stream.FakeRepository;
import com.example.stream.model.EnrichEnd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class RunRule implements Consumer<EnrichEnd> {

    private final FakeRepository repository;

    @Override
    public void accept(EnrichEnd enrichEnd) {
        repository.findEnrichEnd(enrichEnd);
        log.info("Running rule for {}", enrichEnd.getKey());
    }
}
