package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryRequest {

	@JsonProperty("query")
	public Query query;

	@JsonProperty()
	public Sort sort;


}


// TODO: finn en måte å konsturere dette opp.

//{
//	"query": {
//	"status": {
//	"option": "online"
//	},
//	"stats": [{
//	"type": "and",
//	"filters": [{
//	"id": "pseudo.pseudo_total_life",
//	"disabled": false,
//	"value": {
//	"min": 20
//	}
//	}, {
//	"id": "pseudo.pseudo_total_elemental_resistance",
//	"disabled": true
//	}
//	],
//	"disabled": false
//	}, {
//	"type": "weight",
//	"filters": [{
//	"id": "explicit.stat_1754445556",
//	"disabled": false
//	}, {
//	"id": "explicit.stat_4067062424",
//	"disabled": false
//	}, {
//	"id": "explicit.stat_2787733863",
//	"disabled": false
//	}, {
//	"id": "explicit.stat_2383797932",
//	"disabled": false
//	}
//	],
//	"disabled": false,
//	"value": {
//	"min": 10
//	}
//	}
//	],
//	"filters": {
//	"req_filters": {
//	"filters": {
//	"lvl": {
//	"min": null,
//	"max": 37
//	}
//	},
//	"disabled": false
//	},
//	"type_filters": {
//	"filters": {
//	"category": {
//	"option": "jewel.abyss"
//	}
//	},
//	"disabled": false
//	},
//	"trade_filters": {
//	"filters": {
//	"price": {
//	"min": null,
//	"max": 15,
//	"option": null
//	},
//	"sale_type": {
//	"option": null
//	}
//	},
//	"disabled": false
//	}
//	}
//	},
//	"sort": {
//	"price": "asc"
//	}
//	}
