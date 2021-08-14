package com.social.network.PayLoad.SignUpRequest;

import java.sql.Date;

public class SignupRequest {

    private String userName;

    private String password;

    private String email;

    private Date birthday;

    public SignupRequest(String userName, String password, String email, Date birthday) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }
}
