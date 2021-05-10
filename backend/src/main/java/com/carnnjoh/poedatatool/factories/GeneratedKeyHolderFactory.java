package com.carnnjoh.poedatatool.factories;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class GeneratedKeyHolderFactory {

    public GeneratedKeyHolderFactory() {
    }

    public KeyHolder newKeyHolder() {
        return new GeneratedKeyHolder();
    }

}
