package com.flys.bible.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

@DatabaseTable(tableName = "chapitre")
public class Chapitre extends BaseEntity implements Comparable<Chapitre>, Comparator<Chapitre> {

    @DatabaseField
    private String nom;

    @DatabaseField
    private int numero;

    @ForeignCollectionField(columnName = "titres", eager = true)
    private Collection<Titre> titres;

    public Chapitre() {
    }

    public Chapitre(String nom, int numero, ForeignCollection<Titre> titres) {
        this.nom = nom;
        this.numero = numero;
        this.titres = titres;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Collection<Titre> getTitres() {
        return titres;
    }

    public void setTitres(Collection<Titre> titres) {
        this.titres = titres;
    }

    @Override
    public String toString() {
        return "Chapitre{" +
                "nom='" + nom + '\'' +
                ", numero=" + numero +
                ", titres=" + titres +
                '}';
    }

    @Override
    public int compareTo(Chapitre o) {
        if(this.getId()==o.getId()){
            return 0;
        }else {
            return -1;
        }

    }

    @Override
    public int compare(Chapitre o1, Chapitre o2) {

        return o2.numero-o1.numero;
    }

   /* @Override
    public Comparator<Chapitre> reversed() {
        return null;
    }*/

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
