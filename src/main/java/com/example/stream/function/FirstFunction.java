package com.example.stream.function;

import com.example.stream.FakeRepository;
import com.example.stream.model.Fus;
import com.example.stream.model.Ro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FirstFunction implements Function<Fus, Ro> {

    private final FakeRepository repository;

    @Override
    public Ro apply(Fus fus) {
        return repository.findRo(fus.getId());
    }
}
