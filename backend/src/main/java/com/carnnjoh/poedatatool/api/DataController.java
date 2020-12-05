package com.carnnjoh.poedatatool.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class DataController {

    private final String BASE_URL = "https://www.pathofexile.com/api/public-stash-tabs";
    private final String userAgent =  "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                                        "(KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
    private RestTemplate template = new RestTemplate();

    @GetMapping("/pollData")
    public String pollData() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",userAgent);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> responseEntity = template.exchange(BASE_URL, HttpMethod.GET, entity, String.class);

        return responseEntity.getBody();
    }
}