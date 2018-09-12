package com.flys.bible.entities;

import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

public class Chapitre {
    private String nom;
    private List<Titre> titres;

    public Chapitre() {
    }

    public Chapitre(String nom, List<Titre> titres) {
        this.nom = nom;
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

    @Override
    public String toString() {
        return "Chapitre{" +
                "nom='" + nom + '\'' +
                ", titres=" + titres +
                '}';
    }
}
