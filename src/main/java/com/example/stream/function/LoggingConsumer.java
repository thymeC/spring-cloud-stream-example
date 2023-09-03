package com.example.stream.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;


@Slf4j
@Configuration
public class LoggingConsumer {

    @Bean
    public Consumer<String> log() {
        return log::info;
    }

}
