/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.model;
import java.util.Date;

/**
 *
 * @author HP
 */

public class CommandeAchat {
    
    private int idCommande;
    private Fournisseur fournisseur;
    private Produit produit;
    private Date dateCommande;
    private int quantite;
    private String statut;

    // Constructeur vide
    public CommandeAchat() {}

    // Constructeur avec paramètres
    public CommandeAchat(Fournisseur fournisseur, Produit produit, 
                          Date dateCommande, int quantite, String statut) {
        this.fournisseur = fournisseur;
        this.produit = produit;
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.statut = statut;
    }

    // Constructeur complet
    public CommandeAchat(int idCommande, Fournisseur fournisseur, Produit produit,
                          Date dateCommande, int quantite, String statut) {
        this.idCommande = idCommande;
        this.fournisseur = fournisseur;
        this.produit = produit;
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.statut = statut;
    }

    // Getters
    public int getIdCommande() { return idCommande; }
    public Fournisseur getFournisseur() { return fournisseur; }
    public Produit getProduit() { return produit; }
    public Date getDateCommande() { return dateCommande; }
    public int getQuantite() { return quantite; }
    public String getStatut() { return statut; }

    // Setters
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }
    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setStatut(String statut) { this.statut = statut; }

    // Méthode métier
    public void validerLivraison() {
        this.statut = "Livrée";
    }

    @Override
    public String toString() {
        return "Commande #" + idCommande + " - " + statut;
    }
}
