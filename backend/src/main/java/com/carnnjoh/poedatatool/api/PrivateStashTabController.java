package com.carnnjoh.poedatatool.api;

import com.carnnjoh.poedatatool.exceptions.StashNotFoundException;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import com.carnnjoh.poedatatool.model.PrivateStashTabRequest;
import com.carnnjoh.poedatatool.services.PrivateStashTabFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/private")
public class PrivateStashTabController {

    @Autowired
    private PrivateStashTabFetcher fetcher;

    @GetMapping("/stash-tab")
    public PrivateStashTab fetchAllStashes(
        @RequestParam String league,
        @RequestParam String realm,
        @RequestParam String accountName,
        @RequestParam int tabIndex,
        @RequestHeader("POESESSID") String poeSessionId
    ) {
        PrivateStashTabRequest request = new PrivateStashTabRequest(
            league, realm, accountName, true, tabIndex, poeSessionId);

        return fetcher
            .fetchStashItems(request)
            .orElseThrow(StashNotFoundException::new);
    }
}
