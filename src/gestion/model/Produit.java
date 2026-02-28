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

public class Produit {
    
    private int idProduit;
    private String libelle;
    private String categorie;
    private double prixAchat;
    private int stock;

    // Constructeur vide
    public Produit() {}

    // Constructeur avec paramètres
    public Produit(String libelle, String categorie, double prixAchat, int stock) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.prixAchat = prixAchat;
        this.stock = stock;
    }

    // Constructeur complet
    public Produit(int idProduit, String libelle, String categorie, double prixAchat, int stock) {
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.categorie = categorie;
        this.prixAchat = prixAchat;
        this.stock = stock;
    }

    // Getters
    public int getIdProduit() { return idProduit; }
    public String getLibelle() { return libelle; }
    public String getCategorie() { return categorie; }
    public double getPrixAchat() { return prixAchat; }
    public int getStock() { return stock; }

    // Setters
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setPrixAchat(double prixAchat) { this.prixAchat = prixAchat; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return libelle;
    }
}