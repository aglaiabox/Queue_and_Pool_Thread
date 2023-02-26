package com.example.queue.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Task {
    String name;
    long mlsec;
}
