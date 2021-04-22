package com.carnnjoh.poedatatool.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class APIUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIUtils.class);

    public static void checkIfRateLimitExceeded(ResponseEntity responseEntity)  {
        if(responseEntity.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
            LOGGER.error("Rate limit exceeded - shutting down the application.");
            System.exit(-1);
        }
    }

}
