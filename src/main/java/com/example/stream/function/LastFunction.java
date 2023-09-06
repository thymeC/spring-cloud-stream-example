package com.example.stream.function;

import com.example.stream.model.Dah;
import com.example.stream.model.Fus;
import com.example.stream.model.Ro;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class LastFunction implements Consumer<Dah> {

    @Override
    public void accept(Dah dah) {

    }
}
