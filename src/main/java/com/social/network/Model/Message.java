package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Message implements Serializable {

    @Id
    @Column(name = "idMessage")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;

    @Basic
    @Column(name = "SendText")
    private String sendText;

    @Basic
    @Column(name = "SendTime")
    private Timestamp sendTime;

    @ManyToOne
    @JoinColumn(name = "User_idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Chat_idChat")
    private Chat chat;

    public Message() {

    }

    public Message(String sendText) {
        this.sendText = sendText;
        sendTime = new Timestamp(System.currentTimeMillis());
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getSendText() {
        return sendText;
    }

    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

}
