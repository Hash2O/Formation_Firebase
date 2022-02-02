package com.flash.formationfirebase.entities;

public class Ingredient {

    private String libelle;

    private int quantite;

    private String recetteId;


    public Ingredient() {
    }


    public Ingredient(String libelle, int quantite, String recetteId) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.recetteId = recetteId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getRecetteId() {
        return recetteId;
    }

    public void setRecetteId(String recetteId) {
        this.recetteId = recetteId;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "libelle='" + libelle + '\'' +
                ", quantite=" + quantite +
                ", recetteId='" + recetteId + '\'' +
                '}';
    }
}
