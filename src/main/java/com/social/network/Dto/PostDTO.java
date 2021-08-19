package com.social.network.Dto;

import java.sql.Timestamp;

public class PostDTO {

    private Timestamp createdDate;

    private String title;

    private String username;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "createdDate=" + createdDate +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
