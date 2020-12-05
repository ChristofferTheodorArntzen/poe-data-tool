package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StashTabs {
    @JsonProperty("next_change_id") public String nextChangeId;
    @JsonProperty("stashes") public List<Stash> stashes;
}
