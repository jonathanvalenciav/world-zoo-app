package com.android.worldzoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animal {
    private int image;
    private String name;
    private String description;
}
