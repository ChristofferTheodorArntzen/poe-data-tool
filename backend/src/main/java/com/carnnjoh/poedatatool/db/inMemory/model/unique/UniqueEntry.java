package com.carnnjoh.poedatatool.db.inMemory.model.unique;

public class UniqueEntry {

	public String name;
	public String type;
	public String text;
	public Flags flags;
	public String disc;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getText() {
		return text;
	}

	public Flags getFlags() {
		return flags;
	}

	public String getDisc() {
		return disc;
	}
}
