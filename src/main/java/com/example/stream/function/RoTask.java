package com.example.stream.function;

import com.example.stream.FakeApi;
import com.example.stream.FakeRepository;
import com.example.stream.model.Dah;
import com.example.stream.model.Ro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RoTask implements Function<Ro, Dah> {

    private final FakeRepository repository;
    private final FakeApi api;

    @Override
    public Dah apply(Ro ro) {
        api.fetch(ro);
        return repository.findDah(ro.getId());
    }
}
