package com.carnnjoh.poedatatool.api.requestobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	private String league;
	private String accountName;
	private String realm;
	private String sessionId;

}
