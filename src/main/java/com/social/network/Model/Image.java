package com.social.network.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Image implements Serializable {

    @Id
    @Column(name = "idImage")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImage;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "Photo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "Publication_idPublication")
    @JsonIgnore
    private Publication publication;

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

}
