package com.social.network.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "role", schema = "network social")
public class Role implements Serializable {

    @Id
    @Column(name = "idRole")
    private int idRole;

    @Basic
    @Column(name = "Name")
    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
