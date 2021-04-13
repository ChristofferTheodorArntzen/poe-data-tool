package com.carnnjoh.poedatatool.db.inMemory.model.constant;


import com.carnnjoh.poedatatool.db.inMemory.model.shared.DataEntry;

public class ConstantEntry extends DataEntry {

	public String id;
	public String text;
	public String image;

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getImage() {
		return image;
	}

}
