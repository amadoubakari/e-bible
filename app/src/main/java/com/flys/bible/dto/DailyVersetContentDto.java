package com.flys.bible.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class DailyVersetContentDto implements Serializable {

    private String url;
    private String human_reference;
    private String html;
    private ArrayList<String> usfms;
    private String text;


    public DailyVersetContentDto() {
    }

    public DailyVersetContentDto(String url, String human_reference, String html, ArrayList<String> usfms, String text) {
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

    public ArrayList<String> getUsfms() {
        return usfms;
    }

    public void setUsfms(ArrayList<String> usfms) {
        this.usfms = usfms;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

     @Override
     public String toString() {
         return "DailyVersetContentDto{" +
                 "url='" + url + '\'' +
                 ", human_reference='" + human_reference + '\'' +
                 ", html='" + html + '\'' +
                 ", usfms=" + usfms +
                 ", text='" + text + '\'' +
                 '}';
     }
 }
