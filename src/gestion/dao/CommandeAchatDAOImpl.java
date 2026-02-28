/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.CommandeAchat;
import gestion.model.Fournisseur;
import gestion.model.Produit;
import gestion.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List; 

/**
 *
 * @author HP
 */

public class CommandeAchatDAOImpl implements CommandeAchatDAO {

    private Connection conn = DBConnection.getConnection();

    @Override
    public void create(CommandeAchat c) {
        String sql = "INSERT INTO commande_achat (id_fournisseur, id_produit, dateCommande, quantite, statut) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getFournisseur().getIdFournisseur());
            ps.setInt(2, c.getProduit().getIdProduit());
            ps.setDate(3, new java.sql.Date(c.getDateCommande().getTime()));
            ps.setInt(4, c.getQuantite());
            ps.setString(5, c.getStatut());
            ps.executeUpdate();
            System.out.println(" Commande ajoutée !");
        } catch (SQLException e) {
            System.out.println(" Erreur ajout commande : " + e.getMessage());
        }
    }

    @Override
    public void update(CommandeAchat c) {
        String sql = "UPDATE commande_achat SET id_fournisseur=?, id_produit=?, dateCommande=?, quantite=?, statut=? WHERE id_commande=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getFournisseur().getIdFournisseur());
            ps.setInt(2, c.getProduit().getIdProduit());
            ps.setDate(3, new java.sql.Date(c.getDateCommande().getTime()));
            ps.setInt(4, c.getQuantite());
            ps.setString(5, c.getStatut());
            ps.setInt(6, c.getIdCommande());
            ps.executeUpdate();
            System.out.println(" Commande modifiée !");
        } catch (SQLException e) {
            System.out.println(" Erreur modification : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        // Vérification règle métier : commande livrée non supprimable
        String check = "SELECT statut FROM commande_achat WHERE id_commande=?";
        try (PreparedStatement ps = conn.prepareStatement(check)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString("statut").equals("Livrée")) {
                System.out.println(" Impossible : commande déjà livrée !");
                return;
            }
        } catch (SQLException e) {
            System.out.println(" Erreur vérification : " + e.getMessage());
            return;
        }

        String sql = "DELETE FROM commande_achat WHERE id_commande=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" Commande supprimée !");
        } catch (SQLException e) {
            System.out.println(" Erreur suppression : " + e.getMessage());
        }
    }

    @Override
    public CommandeAchat findById(int id) {
        String sql = "SELECT ca.*, f.nom, f.ville, f.telephone, p.libelle, p.categorie, p.prixAchat, p.stock " +
                     "FROM commande_achat ca " +
                     "JOIN fournisseur f ON ca.id_fournisseur = f.id_fournisseur " +
                     "JOIN produit p ON ca.id_produit = p.id_produit " +
                     "WHERE ca.id_commande=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println(" Erreur recherche : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<CommandeAchat> findAll() {
        this.conn = DBConnection.getConnection();
        List<CommandeAchat> liste = new ArrayList<>();
        String sql = "SELECT ca.*, f.nom, f.ville, f.telephone, p.libelle, p.categorie, p.prixAchat, p.stock " +
                     "FROM commande_achat ca " +
                     "JOIN fournisseur f ON ca.id_fournisseur = f.id_fournisseur " +
                     "JOIN produit p ON ca.id_produit = p.id_produit " +
                     " ORDER BY ca.id_commande ASC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(" Erreur liste commandes : " + e.getMessage());
        }
        return liste;
    }

    @Override
    public List<CommandeAchat> findByStatut(String statut) {
        List<CommandeAchat> liste = new ArrayList<>();
        String sql = "SELECT ca.*, f.nom, f.ville, f.telephone, p.libelle, p.categorie, p.prixAchat, p.stock " +
                     "FROM commande_achat ca " +
                     "JOIN fournisseur f ON ca.id_fournisseur = f.id_fournisseur " +
                     "JOIN produit p ON ca.id_produit = p.id_produit " +
                     "WHERE ca.statut=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, statut);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(" Erreur filtre statut : " + e.getMessage());
        }
        return liste;
    }

    @Override
    public List<CommandeAchat> findByFournisseur(int idFournisseur) {
        List<CommandeAchat> liste = new ArrayList<>();
        String sql = "SELECT ca.*, f.nom, f.ville, f.telephone, p.libelle, p.categorie, p.prixAchat, p.stock " +
                     "FROM commande_achat ca " +
                     "JOIN fournisseur f ON ca.id_fournisseur = f.id_fournisseur " +
                     "JOIN produit p ON ca.id_produit = p.id_produit " +
                     "WHERE ca.id_fournisseur=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idFournisseur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(" Erreur filtre fournisseur : " + e.getMessage());
        }
        return liste;
    }

    @Override
    public List<CommandeAchat> findByPeriode(String dateDebut, String dateFin) {
        List<CommandeAchat> liste = new ArrayList<>();
        String sql = "SELECT ca.*, f.nom, f.ville, f.telephone, p.libelle, p.categorie, p.prixAchat, p.stock " +
                     "FROM commande_achat ca " +
                     "JOIN fournisseur f ON ca.id_fournisseur = f.id_fournisseur " +
                     "JOIN produit p ON ca.id_produit = p.id_produit " +
                     "WHERE ca.dateCommande BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dateDebut);
            ps.setString(2, dateFin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println(" Erreur filtre période : " + e.getMessage());
        }
        return liste;
    }

    @Override
    public void validerLivraison(int idCommande) {
        // Récupérer la commande
        CommandeAchat commande = findById(idCommande);
        if (commande == null) {
            System.out.println(" Commande introuvable !");
            return;
        }
        if (commande.getStatut().equals("Livrée")) {
            System.out.println(" Commande déjà livrée !");
            return;
        }

        // Mettre à jour statut
        String sql1 = "UPDATE commande_achat SET statut='Livrée' WHERE id_commande=?";
        // Mettre à jour stock automatiquement
        String sql2 = "UPDATE produit SET stock = stock + ? WHERE id_produit=?";

        try {
            conn.setAutoCommit(false); // Transaction

            try (PreparedStatement ps1 = conn.prepareStatement(sql1);
                 PreparedStatement ps2 = conn.prepareStatement(sql2)) {

                ps1.setInt(1, idCommande);
                ps1.executeUpdate();

                ps2.setInt(1, commande.getQuantite());
                ps2.setInt(2, commande.getProduit().getIdProduit());
                ps2.executeUpdate();

                conn.commit(); // Valider les 2 opérations ensemble
                System.out.println(" Livraison validée + stock mis à jour !");
            }
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println(" Erreur validation livraison : " + e.getMessage());
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException e) {}
        }
    }

    // Méthode utilitaire pour mapper un ResultSet vers CommandeAchat
    private CommandeAchat mapResultSet(ResultSet rs) throws SQLException {
        Fournisseur f = new Fournisseur(
            rs.getInt("id_fournisseur"),
            rs.getString("nom"),
            rs.getString("ville"),
            rs.getString("telephone")
        );
        Produit p = new Produit(
            rs.getInt("id_produit"),
            rs.getString("libelle"),
            rs.getString("categorie"),
            rs.getDouble("prixAchat"),
            rs.getInt("stock")
        );
        return new CommandeAchat(
            rs.getInt("id_commande"),
            f, p,
            rs.getDate("dateCommande"),
            rs.getInt("quantite"),
            rs.getString("statut")
        );
    }
    
    public List<Object[]> getQuantiteParCategorie() {
    List<Object[]> result = new java.util.ArrayList<>();
    String sql = "SELECT p.categorie, SUM(ca.quantite) as total " +
                 "FROM commande_achat ca " +
                 "JOIN produit p ON ca.id_produit = p.id_produit " +
                 "GROUP BY p.categorie";
    try (java.sql.Connection conn = DBConnection.getConnection();
         java.sql.Statement st = conn.createStatement();
         java.sql.ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            result.add(new Object[]{rs.getString("categorie"), rs.getInt("total")});
        }
    } catch (Exception e) {
        System.out.println("Erreur statistiques catégorie : " + e.getMessage());
    }
    return result;
}

public List<Object[]> getDepensesParMois() {
    List<Object[]> result = new java.util.ArrayList<>();
    String sql = "SELECT DATE_FORMAT(ca.dateCommande, '%Y-%m') as mois, " +
                 "SUM(ca.quantite * p.prixAchat) as total " +
                 "FROM commande_achat ca " +
                 "JOIN produit p ON ca.id_produit = p.id_produit " +
                 "GROUP BY mois ORDER BY mois ASC";
    try (java.sql.Connection conn = DBConnection.getConnection();
         java.sql.Statement st = conn.createStatement();
         java.sql.ResultSet rs = st.executeQuery(sql)) {
        while (rs.next()) {
            result.add(new Object[]{rs.getString("mois"), rs.getDouble("total")});
        }
    } catch (Exception e) {
        System.out.println("Erreur statistiques mois : " + e.getMessage());
    }
    return result;
}
}