package com.example.stream.model;

import lombok.Data;

@Data
public abstract class AbstractMessage {
    private String key;

    public abstract void generateKey();
}
