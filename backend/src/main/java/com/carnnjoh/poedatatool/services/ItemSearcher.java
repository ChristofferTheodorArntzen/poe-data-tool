package com.carnnjoh.poedatatool.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemSearcher {

	//Henter alle stats i spillet?
	//https://www.pathofexile.com/api/trade/data/stats

	// Henter statiske props
	// https://www.pathofexile.com/api/trade/data/static

	// Bygge query component

	// Sende søke på dette apiet
	// https://www.pathofexile.com/api/trade/search/{league}

	// få resultatsetet
	// https://www.pathofexile.com/api/trade/fetch/{items}

	private final RestTemplate template = new RestTemplate();

	public void searchForItem() {



	}

}
