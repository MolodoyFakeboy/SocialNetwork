package com.social.network.Dto;

import java.sql.Timestamp;

public class PublicationDTO {

    private Timestamp createdTime;

    private String info;

    private int publicationID;

    public int getPublicationID() {
        return publicationID;
    }

    public void setPublicationID(int publicationID) {
        this.publicationID = publicationID;
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

    @Override
    public String toString() {
        return "PublicationDTO{" +
                "createdTime=" + createdTime +
                ", info='" + info + '\'' +
                '}';
    }
}

