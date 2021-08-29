package com.social.network.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publication implements Serializable {

    @Id
    @Column(name = "idPublication")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "createTime")
    private Timestamp createdTime;

    @Basic
    @Column(name = "information")
    private String info;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_has_publication",
            joinColumns = {@JoinColumn(name = "Publication_idPublication")},
            inverseJoinColumns = {@JoinColumn(name = "Group_idGroup")}
    )
    private Set<Group> listGroup;

    @OneToMany(mappedBy = "publication")
    private Set<Image> images;

    @OneToMany(mappedBy = "publication")
    private Set<Comment> comments;

    public Publication(String info) {
        this.info = info;
        this.createdTime = new Timestamp(System.currentTimeMillis());
        listGroup= new HashSet<>();
        images = new HashSet<>();
        comments = new HashSet<>();
    }

    public Publication() {

    }

    public Set<Comment> getComments() {
        return comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Group> getListGroup() {
        return listGroup;
    }

    public void setListGroup(Set<Group> listGroup) {
        this.listGroup = listGroup;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
