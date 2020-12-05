package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder(value = {"string", "values"})
public class Values {

	public String modifierValue;
	public int modifierValueIndex;

}
