package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sockets {

	@JsonProperty("group")
	Integer group;

	@JsonProperty("attr")
	String attributeSocket;

	@JsonProperty("sColour")
	String socketColor;


}
