package com.flys.bible.dto;


import java.io.Serializable;


public class DailyVersetDto implements Serializable {

    private DailyVersetImageDto image;
    private int day;
    private DailyVersetContentDto verse;

    public DailyVersetDto() {
    }

    public DailyVersetDto(DailyVersetImageDto image, int day, DailyVersetContentDto verse) {
        this.image = image;
        this.day = day;
        this.verse = verse;
    }

    public DailyVersetImageDto getImage() {
        return image;
    }

    public void setImage(DailyVersetImageDto image) {
        this.image = image;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public DailyVersetContentDto getVerse() {
        return verse;
    }

    public void setVerse(DailyVersetContentDto verse) {
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


