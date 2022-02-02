package com.flash.formationfirebase.entities;

public class Recette {

    private String libelle;

    private String description;

    private String utilisateurId;

    /**
     * Constructeur sans param
     */
    public Recette() {

    }

    public Recette(String libelle, String description, String utilisateurId) {
        this.libelle = libelle;
        this.description = description;
        this.utilisateurId = utilisateurId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }


    @Override
    public String toString() {
        return "Recette{" +
                "libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", utilisateurId='" + utilisateurId + '\'' +
                '}';
    }
}
