package com.flys.bible.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.List;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

@DatabaseTable(tableName = "titre")
public class Titre extends BaseEntity  {

    @DatabaseField
    private String nom;

    @DatabaseField(columnName="chapitre",foreign = true, foreignAutoRefresh = true)
    private Chapitre chapitre;

    @ForeignCollectionField(columnName = "titres", eager = true)
    private Collection<Verset> versets;

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

    public Collection<Verset> getVersets() {
        return versets;
    }

    public void setVersets(Collection<Verset> versets) {
        this.versets = versets;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }

    @Override
    public String toString() {
        return "Titre{" +
                "nom='" + nom + '\'' +
                ", chapitre=" + chapitre +
                ", versets=" + versets +
                '}';
    }
}
