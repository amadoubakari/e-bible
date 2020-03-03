package com.flys.bible.entities;

import java.io.Serializable;
import java.util.List;

public class DailyVersetData implements Serializable {
    private List<DailyVerset> data;

    public DailyVersetData() {
    }

    public DailyVersetData(List<DailyVerset> data) {
        this.data = data;
    }

    public List<DailyVerset> getData() {
        return data;
    }

    public void setData(List<DailyVerset> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DailyVersetData{" +
                "data=" + data +
                '}';
    }
}
