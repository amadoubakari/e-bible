package com.flys.bible.dto;

import java.io.Serializable;

public class DailyVersetImageDto implements Serializable {

    private String url;
    private String attribution;

    public DailyVersetImageDto() {
    }

    public DailyVersetImageDto(String url, String attribution) {
        this.url = url;
        this.attribution = attribution;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    @Override
    public String toString() {
        return "DailyVersetImage{" +
                "url='" + url + '\'' +
                ", attribution='" + attribution + '\'' +
                '}';
    }
}
