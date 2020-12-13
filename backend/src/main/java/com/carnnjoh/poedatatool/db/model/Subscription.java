package com.carnnjoh.poedatatool.db.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Array;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subscription {

	@Ignore
	private int pk;

	private Array tabIds;
	private double threshold = 0;
}
