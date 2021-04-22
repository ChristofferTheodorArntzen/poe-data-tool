package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

public enum QueryFilterType {

	AND("and"),
	NOT("not"),
	COUNT("count"),
	WEIGHT("weight"),
	IF("if");

	String text;

	QueryFilterType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
