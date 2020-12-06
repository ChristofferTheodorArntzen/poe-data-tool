package com.carnnjoh.poedatatool.model;

public class PrivateStashTabRequest {

    public final String league;
    public final String realm;
    public final String accountName;
    public final int tabs;
    public final int tabIndex;
    public final String poeSessionId;

    public PrivateStashTabRequest(
        String league,
        String realm,
        String accountName,
        boolean showTabVisuals,
        int tabIndex,
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
