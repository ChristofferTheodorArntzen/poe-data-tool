package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.PrivateStashTabRequest;
import com.carnnjoh.poedatatool.model.PrivateTabStash;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Optional;

@Component
public class PrivateStashTabFetcher {

    private final String BASE_URL = "https://www.pathofexile.com/character-window";
    private final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64)";

    private final RestTemplate template = new RestTemplate();

    public Optional<PrivateTabStash> fetchStashItems(PrivateStashTabRequest request) {
        HttpEntity<String> entity = new HttpEntity<>(createHeaders(request.poeSessionId));
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/get-stash-items")
            .queryParam("league", request.league)
            .queryParam("realm", request.realm)
            .queryParam("accountName", request.accountName)
            .queryParam("tabs", request.tabs)
            .queryParam("tabIndex", request.tabIndex);

        ResponseEntity<PrivateTabStash> responseEntity = template
            .exchange(builder.toUriString(), HttpMethod.GET, entity, PrivateTabStash.class);

        return (responseEntity.getStatusCode() == HttpStatus.OK)
            ? Optional.of(responseEntity.getBody())
            : Optional.empty();
    }

    private HttpHeaders createHeaders(String poeSessionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", USER_AGENT);
        headers.add("Cookie", "POESESSID=" + poeSessionId);
        headers.add("accept", "*/*");
        return headers;
    }
}
