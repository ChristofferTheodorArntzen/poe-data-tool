package com.carnnjoh.poedatatool.db.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//TODO: add generic type T instead of pk
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSuccessResult extends SuccessResult {

	private Integer pk;

}
