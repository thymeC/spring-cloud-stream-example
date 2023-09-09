package com.example.stream.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnrichEnd extends AbstractMessage {
    private String id;
    private String type;

    @Override
    public void generateKey() {
        setKey("EnrichEnd-%s-%s".formatted(getId(), getType()));
    }
}
