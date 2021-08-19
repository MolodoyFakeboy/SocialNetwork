package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        if (idPost != post.idPost) return false;
        if (createdDate != null ? !createdDate.equals(post.createdDate) : post.createdDate != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idPost;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
