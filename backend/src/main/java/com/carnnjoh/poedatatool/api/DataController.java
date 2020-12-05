package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.exceptions.StashNotFoundException;
import com.carnnjoh.poedatatool.model.StashTab;
import com.carnnjoh.poedatatool.services.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataFetcher dataFetcher;

    @GetMapping("/stash")
    public List<StashTab> fetchAllStashes() {
        return dataFetcher.fetchStash().orElseThrow(StashNotFoundException::new);
    }

    @GetMapping("/stash/{id}")
    public StashTab fetchStashesById(@PathVariable String id) {
        return dataFetcher.fetchStash(id).orElseThrow(StashNotFoundException::new);
    }
}
