package com.social.network.dto;

import java.util.Date;
import java.util.List;

public class UserDTO {

    private String username;

    private String bio;

    private Date birthday;

    private int userID;

    private List<PostDTO> postsFromUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<PostDTO> getPostsFromUser() {
        return postsFromUser;
    }

    public void setPostsFromUser(List<PostDTO> postsFromUser) {
        this.postsFromUser = postsFromUser;
    }
}
