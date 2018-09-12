package com.flys.bible.entities;

import java.io.Serializable;

/**
 * Created by User on 07/09/2018.
 */

public class Data implements Serializable {

    private int chapitre;
    private String datas;

    public Data() {
    }

    public Data(int chapitre, String datas) {
        this.chapitre = chapitre;
        this.datas = datas;
    }

    public int getChapitre() {
        return chapitre;
    }

    public void setChapitre(int chapitre) {
        this.chapitre = chapitre;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Data{" +
                "chapitre=" + chapitre +
                ", datas='" + datas + '\'' +
                '}';
    }
}
