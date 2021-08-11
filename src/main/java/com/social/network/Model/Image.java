package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

@Entity
public class Image implements Serializable {

    @Id
    @Column(name = "idImage")
    private int idImage;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "Photo")
    private byte[] photo;

    @ManyToMany(mappedBy = "images")
    private Set<Post> posts;


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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (idImage != image.idImage) return false;
        if (name != null ? !name.equals(image.name) : image.name != null) return false;
        if (!Arrays.equals(photo, image.photo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idImage;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}