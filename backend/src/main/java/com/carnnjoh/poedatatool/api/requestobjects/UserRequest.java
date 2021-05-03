package com.carnnjoh.poedatatool.api.requestobjects;

public class UserRequest {

	private String league;
	private String accountName;
	private String realm;
	private String sessionId;

	public UserRequest(String league, String accountName, String realm, String sessionId) {
		this.league = league;
		this.accountName = accountName;
		this.realm = realm;
		this.sessionId = sessionId;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
