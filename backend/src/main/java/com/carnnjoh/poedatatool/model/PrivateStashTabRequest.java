package com.carnnjoh.poedatatool.model;

public class PrivateStashTabRequest {

    public final String league;
    public final String realm;
    public final String accountName;
    public final int tabs;
    public Integer tabIndex;
    public final String poeSessionId;

    public PrivateStashTabRequest(
        String league,
        String realm,
        String accountName,
        int tabs,
        String poeSessionId
    ) {
        this.league = league;
        this.realm = realm;
        this.accountName = accountName;
        this.tabs = tabs;
        tabIndex = null;
        this.poeSessionId = poeSessionId;
    }

    public PrivateStashTabRequest(
        String league,
        String realm,
        String accountName,
        boolean showTabVisuals,
        Integer tabIndex,
        String poeSessionId
    ) {
        this.league = league;
        this.realm = realm;
        this.accountName = accountName;
        this.tabs = showTabVisuals ? 1 : 0;
        this.tabIndex = tabIndex;
        this.poeSessionId = poeSessionId;
    }
}
