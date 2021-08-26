package com.social.network.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
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
    @Column(name = "bio")
    private String bio;

    @Basic
    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Role_idRole")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_public",
            joinColumns = {@JoinColumn(name = "User_idUser")},
            inverseJoinColumns = {@JoinColumn(name = "Public_publicID")}
    )
    private Set<Group>communities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "User_idUser", referencedColumnName = "idUser", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "User_idFrend", referencedColumnName = "idUser", nullable = false))
    private Set<User>friends;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_has_chat",
            joinColumns = {@JoinColumn(name = "User_idUser")},
            inverseJoinColumns = {@JoinColumn(name = "Chat_idChat")}
    )
    private Set<Chat>chats;

    @OneToMany(mappedBy = "user")
    private Set<Message>messages;

    @OneToMany(mappedBy = "user")
    private Set<Post>posts;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;


    public User() {

    }

    public User(String username, String password, String email, Date birthday) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        communities = new HashSet<>();
        posts = new HashSet<>();
        friends = new HashSet<>();
        chats = new HashSet<>();
        messages = new HashSet<>();
        comments = new HashSet<>();
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

    public Set<Group> getCommunities() {
        return communities;
    }

    public void setCommunities(Set<Group> communities) {
        this.communities = communities;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
