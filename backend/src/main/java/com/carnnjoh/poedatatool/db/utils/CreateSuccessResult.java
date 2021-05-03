package com.carnnjoh.poedatatool.db.utils;

public class CreateSuccessResult extends SuccessResult {

	private Integer pk;

	public CreateSuccessResult(Integer pk) {
		this.pk = pk;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}
}
