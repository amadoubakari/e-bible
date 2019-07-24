package com.flys.bible.entities;


import java.io.Serializable;

public class DailyVerset implements Serializable {
    private DailyVersetImage image;
    private int day;
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

    private void setVerse(DailyVersetContent verse) {
        this.verse = verse;
    }

    public static class DailyVersetImage {
        private String url;
        private String attribution;

        public DailyVersetImage() {
        }

        public DailyVersetImage(String url, String attribution) {
            this.url = url;
            this.attribution = attribution;
        }
    }

    public static class DailyVersetContent {
        private String url;
        String human_reference;
        private String html;
        private String[] usfms;
        private String text;

        public DailyVersetContent() {
        }

        public DailyVersetContent(String url, String human_reference, String html, String[] usfms, String text) {
            this.url = url;
            this.human_reference = human_reference;
            this.html = html;
            this.usfms = usfms;
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHuman_reference() {
            return human_reference;
        }

        public void setHuman_reference(String human_reference) {
            this.human_reference = human_reference;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String[] getUsfms() {
            return usfms;
        }

        public void setUsfms(String[] usfms) {
            this.usfms = usfms;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}


