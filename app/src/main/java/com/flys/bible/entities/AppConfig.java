package com.flys.bible.entities;

import com.flys.bible.entities.BaseEntity;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "appConfig")
public class AppConfig extends BaseEntity {

    @DatabaseField
    private boolean installed;

    public AppConfig() {
    }

    public AppConfig(boolean installed) {
        this.installed = installed;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "installed=" + installed +
                '}';
    }
}
