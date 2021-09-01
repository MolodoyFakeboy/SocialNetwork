package com.social.network.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Chat implements Serializable {
    @Id
    @Column(name = "idChat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChat;

    @Basic
    @Column(name = "Name")
    private String name;

    @ManyToMany(mappedBy = "chats")
    @JsonIgnore
    private Set<User> users;

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private Set<Message> messages;

    public Chat(String name) {
        this.name = name;
        users = new HashSet<>();
        messages = new HashSet<>();
    }

    public Chat() {

    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
