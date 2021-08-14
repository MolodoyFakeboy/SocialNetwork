package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Group implements Serializable {

    @Id
    @Column(name = "idGroup")
    private int idGroup;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "numberSubscribers")
    private int numberSubscribers;

    @ManyToMany(mappedBy = "groupList")
    private Set<User>users;

    @ManyToMany(mappedBy = "groups")
    private Set<Post> posts;

    public Group() {
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        users = new HashSet<>();
        posts = new HashSet<>();

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (idGroup != group.idGroup) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (description != null ? !description.equals(group.description) : group.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
