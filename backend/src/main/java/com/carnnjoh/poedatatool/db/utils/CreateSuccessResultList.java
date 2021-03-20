package com.carnnjoh.poedatatool.db.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSuccessResultList extends Result {

	private List<CreateSuccessResult> createSuccessResultList;

}
