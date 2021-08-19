package com.social.network.Dto;

import java.sql.Timestamp;

public class PublicationDTO {

    private Timestamp createdTime;

    private String info;

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

