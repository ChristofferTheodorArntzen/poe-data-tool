package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DivinationLayout {

    @JsonProperty("cards")
    public List<Card> cards;

    @JsonProperty("h")
    public int h;

    @JsonProperty("w")
    public int w;

    @JsonProperty("padx")
    public int padx;

    @JsonProperty("pady")
    public int pady;

    @JsonProperty("scale")
    public Float scale;
}
