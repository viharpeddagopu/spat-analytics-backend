package com.SPATanalytics.backend.model;

public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest() {
        // default constructor required by Jackson
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
