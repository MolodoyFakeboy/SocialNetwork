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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;
        if (idMessage != message.idMessage) return false;
        if (sendText != null ? !sendText.equals(message.sendText) : message.sendText != null) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMessage;
        result = 31 * result + (sendText != null ? sendText.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        return result;
    }
}
