package com.example.stream.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Fus extends AbstractMessage{
    private String id;

    @Override
    public void generateKey() {
        setKey("Fus-%s".formatted(getId()));
    }
}
