package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.StashTab;
import com.carnnjoh.poedatatool.model.StashTabs;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PublicStashTabFetcher {

	private final String BASE_URL = "https://www.pathofexile.com/api";
	private final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64)";

	private final RestTemplate template = new RestTemplate();

	public Optional<StashTab> fetchStash(String id) {
		Optional<List<StashTab>> stashes = fetchStash();
		if (stashes.isPresent()) {
			return stashes
				.get()
				.stream()
				.filter(a -> a.id.equals(id))
				.findAny();
		} else {
			return Optional.empty();
		}
	}

	public Optional<StashTabs> fetchPublicStashTabs(String nextChangeId) {

		HttpEntity<String> entity = new HttpEntity<>("parameters", createHeaders());

		ResponseEntity<StashTabs> responseEntity = template
			.exchange(createQueryParams(nextChangeId), HttpMethod.GET, entity, StashTabs.class);
		return (responseEntity.getStatusCode() == HttpStatus.OK)
			? Optional.of(responseEntity.getBody())
			: Optional.empty();
	}

	public Optional<List<StashTab>> fetchStash() {
		HttpEntity<String> entity = new HttpEntity<>("parameters", createHeaders());
		ResponseEntity<StashTabs> responseEntity = template
			.exchange(BASE_URL + "/public-stash-tabs", HttpMethod.GET, entity, StashTabs.class);

		return (responseEntity.getStatusCode() == HttpStatus.OK)
			? Optional.of(responseEntity.getBody().stashes)
			: Optional.empty();
	}

	private String createQueryParams(String nextChangeId) {

		UriComponentsBuilder builder = UriComponentsBuilder
			.fromHttpUrl(BASE_URL + "/public-stash-tabs")
			.queryParam("id", nextChangeId);

		return builder.toUriString();
	}

	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", USER_AGENT);
		return headers;
	}
}
