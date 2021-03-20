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

	private Integer pk;
	private String league;
	private String accountName;
	private String realm;
	private String sessionId;

	public User(String league, String accountName, String realm, String sessionId) {
		this.league = league;
		this.accountName = accountName;
		this.realm = realm;
		this.sessionId = sessionId;
	}

	public User copy() {
		return new User(this.league, this.accountName, this.realm, this.sessionId);
	}

	@Override
	public String toString() {
		return "User{" +
			"pk=" + pk +
			", league='" + league + '\'' +
			", accountName='" + accountName + '\'' +
			", realm='" + realm + '\'' +
			", sessionId='" + sessionId + '\'' +
			'}';
	}
}
