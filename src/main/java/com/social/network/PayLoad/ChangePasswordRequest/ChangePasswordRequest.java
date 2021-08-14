package com.social.network.PayLoad.ChangePasswordRequest;

public class ChangePasswordRequest {

    private String UserName;

    private String OldPassword;

    private String Password;

    public ChangePasswordRequest(String userName, String oldPassword, String password) {
        UserName = userName;
        OldPassword = oldPassword;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public String getPassword() {
        return Password;
    }
}
