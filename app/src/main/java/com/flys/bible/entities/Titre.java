package com.flys.bible.entities;

import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

public class Titre {

    private String nom;
    private List<Verset> versets;

    public Titre() {
    }

    public Titre(String nom, List<Verset> versets) {
        this.nom = nom;
        this.versets = versets;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Verset> getVersets() {
        return versets;
    }

    public void setVersets(List<Verset> versets) {
        this.versets = versets;
    }

    @Override
    public String toString() {
        return "Titre{" +
                "nom='" + nom + '\'' +
                ", versets=" + versets +
                '}';
    }
}
