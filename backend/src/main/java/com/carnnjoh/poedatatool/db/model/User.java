package com.carnnjoh.poedatatool.db.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int pk;
	private String league;
	private String accountName;
	private String realm;
	private String sessionId;

}
