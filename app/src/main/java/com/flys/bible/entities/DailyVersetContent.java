package com.flys.bible.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "dailyVersetContent")
public class DailyVersetContent extends BaseEntity {

    @DatabaseField
    private String url;
    @DatabaseField
    String human_reference;
    @DatabaseField
    private String html;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private String usfms;
    @DatabaseField
    private String text;


    public DailyVersetContent() {
    }

    public DailyVersetContent(String url, String human_reference, String html, String usfms, String text) {
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

    public String getUsfms() {
        return usfms;
    }

    public void setUsfms(String usfms) {
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
        return "DailyVersetContent{" +
                "url='" + url + '\'' +
                ", human_reference='" + human_reference + '\'' +
                ", html='" + html + '\'' +
                ", usfms=" + usfms +
                ", text='" + text + '\'' +
                '}';
    }
}
