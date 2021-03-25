package com.carnnjoh.poedatatool.db.model;

/**
 * Entity to save, update and delete user data
 */
public class User {

	private Integer pk;
	private String league;
	private String accountName;
	private String realm;
	private String sessionId;

	public User() {
	}

	public User(Integer pk, String league, String accountName, String realm, String sessionId) {
		this.pk = pk;
		this.league = league;
		this.accountName = accountName;
		this.realm = realm;
		this.sessionId = sessionId;
	}

	public User(String league, String accountName, String realm, String sessionId) {
		this.league = league;
		this.accountName = accountName;
		this.realm = realm;
		this.sessionId = sessionId;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
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
