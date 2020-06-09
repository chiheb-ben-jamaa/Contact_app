package com.m1ticit.contact_app.activites;

public class contact {
    private String nom;
    private String prenom;
    private String profession;

    public contact() {
    }

    public contact(String nom, String prenom, String profession) {
        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "contact{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
