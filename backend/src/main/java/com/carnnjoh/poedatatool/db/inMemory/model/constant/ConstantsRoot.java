package com.carnnjoh.poedatatool.db.inMemory.model.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConstantsRoot {

	@JsonProperty("result")
	public List<Constant> constants;

}
