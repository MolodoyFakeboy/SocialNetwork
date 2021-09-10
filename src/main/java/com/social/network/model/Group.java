package com.social.network.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Public")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publicID")
    private int idGroup;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "number_subscribers")
    private int subscribers;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "communities")
    @JsonIgnore
    private Set<User>users;

    @ManyToMany(mappedBy = "listGroup")
    @JsonIgnore
    private Set<Publication> publications;

    public Group() {

    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        this.users = new HashSet<>();
        this.publications = new HashSet<>();
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

}

