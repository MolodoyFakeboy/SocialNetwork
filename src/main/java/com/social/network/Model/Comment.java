package com.social.network.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Comment implements Serializable {

    @Id
    @Column(name = "idComment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;

    @Basic
    @Column(name = "Message")
    private String message;

    @Basic
    @Column(name = "createTime")
    private Timestamp dataCreate;

    @ManyToOne
    @JoinColumn(name = "User_idUser")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "Publication_idPublication")
    @JsonIgnore
    private Publication publication;

    public Comment() {

    }

    public Comment(String message) {
        this.message = message;
        dataCreate = new Timestamp(System.currentTimeMillis());
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Timestamp dataCreate) {
        this.dataCreate = dataCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
