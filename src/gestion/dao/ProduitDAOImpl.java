/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.Produit;
import gestion.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */

public class ProduitDAOImpl implements ProduitDAO {

    private Connection conn = DBConnection.getConnection();

    @Override
    public void create(Produit p) {
        String sql = "INSERT INTO produit (libelle, categorie, prixAchat, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getLibelle());
            ps.setString(2, p.getCategorie());
            ps.setDouble(3, p.getPrixAchat());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
            System.out.println(" Produit ajouté !");
        } catch (SQLException e) {
            System.out.println(" Erreur ajout produit : " + e.getMessage());
        }
    }

    @Override
    public void update(Produit p) {
        String sql = "UPDATE produit SET libelle=?, categorie=?, prixAchat=?, stock=? WHERE id_produit=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getLibelle());
            ps.setString(2, p.getCategorie());
            ps.setDouble(3, p.getPrixAchat());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdProduit());
            ps.executeUpdate();
            System.out.println(" Produit modifié !");
        } catch (SQLException e) {
            System.out.println(" Erreur modification : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        // Vérification règle métier
        String check = "SELECT COUNT(*) FROM commande_achat WHERE id_produit=?";
        try (PreparedStatement ps = conn.prepareStatement(check)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println(" Impossible : ce produit est lié à des commandes !");
                return;
            }
        } catch (SQLException e) {
            System.out.println(" Erreur vérification : " + e.getMessage());
            return;
        }

        String sql = "DELETE FROM produit WHERE id_produit=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" Produit supprimé !");
        } catch (SQLException e) {
            System.out.println(" Erreur suppression : " + e.getMessage());
        }
    }

    @Override
    public Produit findById(int id) {
        String sql = "SELECT * FROM produit WHERE id_produit=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("libelle"),
                    rs.getString("categorie"),
                    rs.getDouble("prixAchat"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println(" Erreur recherche : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Produit> findAll() {
        this.conn = DBConnection.getConnection();
        List<Produit> liste = new ArrayList<>();
        String sql = "SELECT * FROM produit ORDER BY id_produit ASC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("libelle"),
                    rs.getString("categorie"),
                    rs.getDouble("prixAchat"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println(" Erreur liste produits : " + e.getMessage());
        }
        return liste;
    }
}
