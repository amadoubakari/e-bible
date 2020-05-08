package com.flys.bible.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "dailyVersetImage")
public class DailyVersetImage extends BaseEntity {

    @DatabaseField
    private String url;
    @DatabaseField
    private String attribution;

    public DailyVersetImage() {
    }

    public DailyVersetImage(String url, String attribution) {
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
