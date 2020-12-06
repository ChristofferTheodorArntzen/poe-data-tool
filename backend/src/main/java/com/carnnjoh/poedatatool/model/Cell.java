package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {
    @JsonProperty("x") public Float x;
    @JsonProperty("y") public Float y;
    @JsonProperty("w") public Float w;
    @JsonProperty("h") public Float h;
}
