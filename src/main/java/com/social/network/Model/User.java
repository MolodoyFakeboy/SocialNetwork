package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @Column(name = "idUser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "username",unique = true)
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "email",unique = true)
    private String email;

    @Basic
    @Column(name = "birthday")
    private Date birthday;

    @ManyToOne
    @JoinColumn(name = "Role_idRole")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_group",
            joinColumns = {@JoinColumn(name = "User_idUser")},
            inverseJoinColumns = {@JoinColumn(name = "Group_idGroup")}
    )
    private Set<Group>groupList;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_has_user",
            joinColumns = {@JoinColumn(name = "User_idUser")},
            inverseJoinColumns = {@JoinColumn(name = "Post_idPost")}
    )
    private Set<Post>posts;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "User_idUser")},
            inverseJoinColumns = {@JoinColumn(name = "User_idFriend")}
    )
    private Set<User>friends;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "chat_has_user",
            joinColumns = {@JoinColumn(name = "User_idUser")},
            inverseJoinColumns = {@JoinColumn(name = "Chat_idChat")}
    )
    private Set<Chat>chats;

    @OneToMany(mappedBy = "user")
    private Set<Message>messages;

    public User() {

    }

    public User(String username, String password, String email, Date birthday) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        groupList = new HashSet<>();
        posts = new HashSet<>();
        friends = new HashSet<>();
        chats = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(Set<Group> groupList) {
        this.groupList = groupList;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (!Objects.equals(birthday, user.birthday)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}