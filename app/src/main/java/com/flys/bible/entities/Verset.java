package com.flys.bible.entities;

/**
 * Created by AMADOU BAKARI on 08/09/2018.
 */

public class Verset {

    private int numero;
    private String description;

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

    @Override
    public String toString() {
        return "Verset{" +
                "numero=" + numero +
                ", description='" + description + '\'' +
                '}';
    }
}
