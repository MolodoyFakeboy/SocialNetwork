package com.social.network.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Post implements Serializable {

    @Id
    @Column(name = "idPost")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;

    @Basic
    @Column(name = "createdDate")
    private Timestamp createdDate;

    @Basic
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "User_idUser")
    private User user;

    public Post() {

    }

    public Post(String title) {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.title = title;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
