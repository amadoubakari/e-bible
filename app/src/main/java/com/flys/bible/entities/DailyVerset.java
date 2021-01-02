package com.flys.bible.entities;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "dailyVerset")
public class DailyVerset extends BaseEntity {

    @DatabaseField(columnName="image",foreign = true, foreignAutoRefresh = true)
    private DailyVersetImage image;
    @DatabaseField
    private int day;
    @DatabaseField(columnName="verse",foreign = true, foreignAutoRefresh = true)
    private DailyVersetContent verse;

    public DailyVerset() {
    }

    public DailyVerset(DailyVersetImage image, int day, DailyVersetContent verse) {
        this.image = image;
        this.day = day;
        this.verse = verse;
    }


    public DailyVersetImage getImage() {
        return image;
    }

    public void setImage(DailyVersetImage image) {
        this.image = image;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public DailyVersetContent getVerse() {
        return verse;
    }

    public void setVerse(DailyVersetContent verse) {
        this.verse = verse;
    }

    @Override
    public String toString() {
        return "DailyVerset{" +
                "image=" + image +
                ", day=" + day +
                ", verse=" + verse +
                '}';
    }
}


