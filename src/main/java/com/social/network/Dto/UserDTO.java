package com.social.network.Dto;

import com.social.network.Model.Post;

import java.util.Date;
import java.util.List;

public class UserDTO {

    private String username;

    private String bio;

    private Date birthday;

    private List<Post> posts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", birthday=" + birthday ;
    }
}
