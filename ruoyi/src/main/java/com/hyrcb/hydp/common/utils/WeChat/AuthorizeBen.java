package com.hyrcb.hydp.common.utils.WeChat;

public class AuthorizeBen {
    private String redirectUri="";
    private String currentUrl="";

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }
}
