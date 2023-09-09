package com.example.stream.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PullRequest extends AbstractMessage{
    private String id;

    @Override
    public void generateKey() {
        setKey("PullRequest-%s".formatted(getId()));
    }
}
