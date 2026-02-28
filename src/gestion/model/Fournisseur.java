/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.model;

/**
 *
 * @author HP
 */
public class Fournisseur {
    
    private int idFournisseur;
    private String nom;
    private String ville;
    private String telephone;

    // Constructeur vide
    public Fournisseur() {}

    // Constructeur avec paramètres
    public Fournisseur(String nom, String ville, String telephone) {
        this.nom = nom;
        this.ville = ville;
        this.telephone = telephone;
    }

    // Constructeur complet
    public Fournisseur(int idFournisseur, String nom, String ville, String telephone) {
        this.idFournisseur = idFournisseur;
        this.nom = nom;
        this.ville = ville;
        this.telephone = telephone;
    }

    // Getters
    public int getIdFournisseur() { return idFournisseur; }
    public String getNom() { return nom; }
    public String getVille() { return ville; }
    public String getTelephone() { return telephone; }

    // Setters
    public void setIdFournisseur(int idFournisseur) { this.idFournisseur = idFournisseur; }
    public void setNom(String nom) { this.nom = nom; }
    public void setVille(String ville) { this.ville = ville; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Override
    public String toString() {
        return nom;
    }
}