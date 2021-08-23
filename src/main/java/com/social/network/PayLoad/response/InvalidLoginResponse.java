package com.social.network.PayLoad.response;

public class InvalidLoginResponse {

    private String username;

    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
