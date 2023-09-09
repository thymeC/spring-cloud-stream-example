package com.example.stream.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Ro extends AbstractMessage {
    private String id;
    private String type;

    @Override
    public void generateKey() {
        setKey("Ro-%s-%s".formatted(getId(), getType()));
    }
}
