package com.carnnjoh.poedatatool.db.inMemory.model.shared;

public abstract class DataEntry {

	public String id;
	public String text;

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}
}
