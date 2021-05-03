package com.carnnjoh.poedatatool.db.utils;

import java.util.List;

public class CreateSuccessResultList extends Result {

	private List<CreateSuccessResult> createSuccessResultList;

	public CreateSuccessResultList(List<CreateSuccessResult> createSuccessResultList) {
		this.createSuccessResultList = createSuccessResultList;
	}

	public List<CreateSuccessResult> getCreateSuccessResultList() {
		return createSuccessResultList;
	}

	public void setCreateSuccessResultList(List<CreateSuccessResult> createSuccessResultList) {
		this.createSuccessResultList = createSuccessResultList;
	}
}
