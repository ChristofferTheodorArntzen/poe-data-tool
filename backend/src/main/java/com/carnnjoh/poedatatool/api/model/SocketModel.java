package com.carnnjoh.poedatatool.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SocketModel {

	@JsonProperty("group")
	Integer group;

	@JsonProperty("attr")
	String attributeSocket;

	@JsonProperty("sColour")
	String socketColor;


}
