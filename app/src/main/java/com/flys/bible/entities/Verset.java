package com.flys.bible.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Comparator;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */
@DatabaseTable(tableName = "verset")
public class Verset extends BaseEntity implements Comparable<Verset>, Comparator<Verset> {

    @DatabaseField
    private int numero;
    @DatabaseField
    private String description;

    @DatabaseField(columnName="titre",foreign = true, foreignAutoRefresh = true)
    private Titre titre;

    public Verset() {
    }

    public Verset(int numero, String description) {
        this.numero = numero;
        this.description = description;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Verset{" +
                "numero=" + numero +
                ", description='" + description + '\'' +
                ", titre=" + titre +
                '}';
    }

    @Override
    public int compareTo(Verset o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public int compare(Verset o1, Verset o2) {
        return o2.numero-o1.numero;
    }
}
