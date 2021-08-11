package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Chat implements Serializable {
    @Id
    @Column(name = "idChat")
    private int idChat;

    @Basic
    @Column(name = "Name")
    private String name;

    @ManyToMany(mappedBy = "chats")
    private Set<User> users;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (idChat != chat.idChat) return false;
        if (name != null ? !name.equals(chat.name) : chat.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idChat;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
