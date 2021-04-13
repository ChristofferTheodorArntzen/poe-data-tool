package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.exceptions.StashNotFoundException;
import com.carnnjoh.poedatatool.model.PublicStashTab;
import com.carnnjoh.poedatatool.services.PublicStashTabFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/public")
public class PublicStashTabController {

    @Autowired
    private PublicStashTabFetcher dataFetcher;

    @GetMapping("/stash-tab")
    public List<PublicStashTab> fetchAllStashes() {
        return dataFetcher.fetchStash().orElseThrow(StashNotFoundException::new);
    }

    @GetMapping("/stash-tab/{id}")
    public PublicStashTab fetchStashesById(@PathVariable String id) {
        return dataFetcher.fetchStash(id).orElseThrow(StashNotFoundException::new);
    }
}
