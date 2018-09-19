package com.flys.bible.entities;

import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

public class Livre extends BaseEntity {

    private String nom;
    private List<Chapitre> chapitres;

    public Livre() {
    }

    public Livre(String nom, List<Chapitre> chapitres) {
        this.nom = nom;
        this.chapitres = chapitres;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "nom='" + nom + '\'' +
                ", chapitres=" + chapitres +
                '}';
    }
}
