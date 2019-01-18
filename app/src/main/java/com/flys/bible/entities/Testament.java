package com.flys.bible.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */
@DatabaseTable(tableName = "testament")
public class Testament extends BaseEntity {

    @DatabaseField
    private String name;

    private List<Livre> livres;

    public Testament() {
    }

    public Testament(String name, List<Livre> livres) {
        this.name = name;
        this.livres = livres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return "Testament{" +
                "name='" + name + '\'' +
                ", livres=" + livres +
                '}';
    }
}
