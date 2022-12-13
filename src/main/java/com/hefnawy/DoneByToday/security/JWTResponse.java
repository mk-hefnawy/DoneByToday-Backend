package com.hefnawy.DoneByToday.security;

public class JWTResponse {
    private String token;
    private String userName;
    private String userRoles;
    private String result;

    public JWTResponse(String token, String userName, String userRoles, String result) {
        this.token = token;
        this.userName = userName;
        this.userRoles = userRoles;
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
