package com.example.stream.function;

import com.example.stream.model.Dah;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class DahRule implements Consumer<Dah> {

    @Override
    public void accept(Dah dah) {

    }
}
