package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse.ListingResponse;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.QueryRequest;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class TradeAPIFetcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeAPIFetcher.class);

    private final String BASE_URL_SEARCH = "http://www.pathofexile.com/api/trade/search/";
    private final String BASE_URL_FETCH = "http://www.pathofexile.com/api/trade/fetch/";

    private final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64)";

    @Autowired
    ObjectMapper objectMapper;

    private final RestTemplate template = new RestTemplate();

    public Optional<QueryResponse> searchForItemIds(String league, String poeSessId, QueryRequest queryRequest) {

        String queryString = createBody(queryRequest);

        LOGGER.debug("Current query request body:");
        LOGGER.debug(queryString);

        if (queryString == null || queryString.isEmpty()) {
            return Optional.empty();
        }

        HttpEntity<String> entity = new HttpEntity<>(queryString, createHeader(poeSessId));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL_SEARCH + league);

        ResponseEntity<QueryResponse> responseEntity = template
                .exchange(builder.toUriString(), HttpMethod.POST, entity, QueryResponse.class);

        APIUtils.checkIfRateLimitExceeded(responseEntity);

        return (responseEntity.getStatusCode() == HttpStatus.OK)
                ? Optional.ofNullable(responseEntity.getBody())
                : Optional.empty();
    }

    public Optional<ListingResponse> fetchListings(String poeSessId, QueryResponse queryResponse) {

        HttpEntity<String> entity = new HttpEntity<>(createHeader(poeSessId));

        //TODO: find out if there is a max limit for how many ids can be concatenated (from small test including every id didn't work?)
        String queryIdString = createIdParam(queryResponse.result);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL_FETCH + queryIdString)
                .queryParam("query", queryResponse.id);

        ResponseEntity<ListingResponse> responseEntity = template
                .exchange(builder.toUriString(), HttpMethod.GET, entity, ListingResponse.class);

        APIUtils.checkIfRateLimitExceeded(responseEntity);

        return (responseEntity.getStatusCode() == HttpStatus.OK)
                ? Optional.ofNullable(responseEntity.getBody())
                : Optional.empty();
    }

    private String createIdParam(String[] itemIds) {

        itemIds = shortenIdArray(itemIds);

        return String.join(",", itemIds);
    }

    //TODO: move to utils class.
    private String[] shortenIdArray(String[] itemIds) {

        List<String> idList = Arrays.asList(itemIds);

        if (idList.size() >= 15) {
            idList = idList.subList(0, 10);
        }

        return idList.toArray(new String[0]);
    }

    //TODO: move to utils class.
    private String createBody(Object object) {
        try {
            ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
            return objectWriter.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return null;
    }

    private HttpHeaders createHeader(String poeSessID) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", USER_AGENT);
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Cookie", "POESESSID=" + poeSessID);
        headers.add("accept", "*/*");
        return headers;
    }

}
