package com.carnnjoh.poedatatool.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class HealthController {

    private ZonedDateTime startTime = ZonedDateTime.now();

    @GetMapping("/health")
    public String health() {
        return "Running since: " + startTime.toString();
    }
}
