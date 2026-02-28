/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.util;
import gestion.dao.ProduitDAOImpl;
import gestion.dao.UtilisateurDAOImpl;
import gestion.dao.FournisseurDAOImpl;
import gestion.dao.FournisseurDAO;
import gestion.dao.CommandeAchatDAO;
import gestion.dao.UtilisateurDAO;
import gestion.dao.ProduitDAO;
import gestion.dao.CommandeAchatDAOImpl;
import gestion.model.Fournisseur;
import gestion.model.CommandeAchat;
import gestion.model.Produit;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */


public class TestDAO {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("         TESTS COUCHE DAO               ");
        System.out.println("========================================");

        // ============================================
        // TEST FOURNISSEUR
        // ============================================
        System.out.println("\n--- TEST FOURNISSEUR ---");
        FournisseurDAO fournisseurDAO = new FournisseurDAOImpl();

        // Create
        Fournisseur f = new Fournisseur("Samsung Maroc", "Casablanca", "0612345678");
        fournisseurDAO.create(f);

        // FindAll
        System.out.println("\n Liste fournisseurs :");
        List<Fournisseur> fournisseurs = fournisseurDAO.findAll();
        for (Fournisseur four : fournisseurs) {
            System.out.println("  → " + four.getIdFournisseur() 
                + " | " + four.getNom() 
                + " | " + four.getVille() 
                + " | " + four.getTelephone());
        }

        // Update
        Fournisseur fUpdate = fournisseurDAO.findAll()
                .stream()
                .filter(x -> x.getNom().equals("Samsung Maroc"))
                .findFirst().orElse(null);
        if (fUpdate != null) {
            fUpdate.setVille("Rabat");
            fournisseurDAO.update(fUpdate);
            System.out.println(" Fournisseur mis à jour !");
        }

        // ============================================
        // TEST PRODUIT
        // ============================================
        System.out.println("\n--- TEST PRODUIT ---");
        ProduitDAO produitDAO = new ProduitDAOImpl();

        // Create
        Produit p = new Produit("Tablette Samsung", "Informatique", 3500.00, 15);
        produitDAO.create(p);

        // FindAll
        System.out.println("\n Liste produits :");
        List<Produit> produits = produitDAO.findAll();
        for (Produit prod : produits) {
            System.out.println("  → " + prod.getIdProduit() 
                + " | " + prod.getLibelle() 
                + " | " + prod.getCategorie() 
                + " | " + prod.getPrixAchat() + " DH"
                + " | Stock: " + prod.getStock());
        }

        // Update
        Produit pUpdate = produitDAO.findAll()
                .stream()
                .filter(x -> x.getLibelle().equals("Tablette Samsung"))
                .findFirst().orElse(null);
        if (pUpdate != null) {
            pUpdate.setPrixAchat(3200.00);
            produitDAO.update(pUpdate);
            System.out.println(" Produit mis à jour !");
        }

        // ============================================
        // TEST COMMANDE
        // ============================================
        System.out.println("\n--- TEST COMMANDE ---");
        CommandeAchatDAO commandeDAO = new CommandeAchatDAOImpl();

        // Récupérer fournisseur et produit existants
        Fournisseur fournisseur = fournisseurDAO.findById(1);
        Produit produit = produitDAO.findById(1);

        // Create commande
        CommandeAchat commande = new CommandeAchat(
            fournisseur, produit, new Date(), 5, "En cours"
        );
        commandeDAO.create(commande);

        // FindAll
        System.out.println("\n Liste commandes :");
        List<CommandeAchat> commandes = commandeDAO.findAll();
        for (CommandeAchat cmd : commandes) {
            System.out.println("  → #" + cmd.getIdCommande()
                + " | " + cmd.getFournisseur().getNom()
                + " | " + cmd.getProduit().getLibelle()
                + " | Qté: " + cmd.getQuantite()
                + " | " + cmd.getStatut());
        }

        // Test filtre par statut
        System.out.println("\n Commandes En cours :");
        List<CommandeAchat> enCours = commandeDAO.findByStatut("En cours");
        enCours.forEach(cmd -> System.out.println("  → #" + cmd.getIdCommande() 
            + " | " + cmd.getProduit().getLibelle()));

        // Test validerLivraison
        System.out.println("\n Test validation livraison commande #2 :");
        System.out.println("Stock avant : " + produitDAO.findById(2).getStock());
        commandeDAO.validerLivraison(2);
        System.out.println("Stock après : " + produitDAO.findById(2).getStock());

        // ============================================
        // TEST UTILISATEUR
        // ============================================
        System.out.println("\n--- TEST UTILISATEUR ---");
        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();

        // Test authentification
        System.out.println("\n Test login admin/1234 :");
        boolean auth = utilisateurDAO.authentifier("admin", "1234");
        System.out.println(auth ? " Login réussi !" : " Login échoué !");

        System.out.println("\n Test login mauvais mot de passe :");
        boolean authFail = utilisateurDAO.authentifier("admin", "wrongpass");
        System.out.println(authFail ? " Login réussi !" : " Login refusé (normal) !");

        System.out.println("\n========================================");
        System.out.println("         TESTS TERMINÉS ✅              ");
        System.out.println("========================================");
    }
}
