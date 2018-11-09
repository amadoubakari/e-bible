package com.flys.bible.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

@DatabaseTable(tableName = "chapitre")
public class Chapitre extends BaseEntity {

    @DatabaseField
    private String nom;
    @DatabaseField
    private int numero;

    private List<Titre> titres;

    public Chapitre() {
    }

    public Chapitre(String nom, List<Titre> titres) {
        this.nom = nom;
        this.titres = titres;
    }

    public Chapitre(String nom, int numero, List<Titre> titres) {
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

    public List<Titre> getTitres() {
        return titres;
    }

    public void setTitres(List<Titre> titres) {
        this.titres = titres;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Chapitre{" +
                "nom='" + nom + '\'' +
                ", numero=" + numero +
                ", titres=" + titres +
                '}';
    }
}
