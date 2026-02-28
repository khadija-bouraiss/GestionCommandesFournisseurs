/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.Fournisseur;
import gestion.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */


public class FournisseurDAOImpl implements FournisseurDAO {

    private Connection conn;

        public FournisseurDAOImpl() {
    this.conn = DBConnection.getConnection();
}

    @Override
    public void create(Fournisseur f) {
        String sql = "INSERT INTO fournisseur (nom, ville, telephone) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNom());
            ps.setString(2, f.getVille());
            ps.setString(3, f.getTelephone());
            ps.executeUpdate();
            System.out.println(" Fournisseur ajouté !");
        } catch (SQLException e) {
            System.out.println(" Erreur ajout fournisseur : " + e.getMessage());
        }
    }

    @Override
    public void update(Fournisseur f) {
        String sql = "UPDATE fournisseur SET nom=?, ville=?, telephone=? WHERE id_fournisseur=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNom());
            ps.setString(2, f.getVille());
            ps.setString(3, f.getTelephone());
            ps.setInt(4, f.getIdFournisseur());
            ps.executeUpdate();
            System.out.println(" Fournisseur modifié !");
        } catch (SQLException e) {
            System.out.println(" Erreur modification : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        // Vérification règle métier : pas de suppression si commandes en cours
        String check = "SELECT COUNT(*) FROM commande_achat WHERE id_fournisseur=? AND statut='En cours'";
        try (PreparedStatement ps = conn.prepareStatement(check)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println(" Impossible : ce fournisseur a des commandes en cours !");
                return;
            }
        } catch (SQLException e) {
            System.out.println(" Erreur vérification : " + e.getMessage());
            return;
        }

        String sql = "DELETE FROM fournisseur WHERE id_fournisseur=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println(" Fournisseur supprimé !");
        } catch (SQLException e) {
            System.out.println(" Erreur suppression : " + e.getMessage());
        }
    }

    @Override
    public Fournisseur findById(int id) {
        String sql = "SELECT * FROM fournisseur WHERE id_fournisseur=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Fournisseur(
                    rs.getInt("id_fournisseur"),
                    rs.getString("nom"),
                    rs.getString("ville"),
                    rs.getString("telephone")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur recherche : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Fournisseur> findAll() {
        List<Fournisseur> liste = new ArrayList<>();
        String sql = "SELECT * FROM fournisseur ORDER BY id_fournisseur ASC";
        this.conn = DBConnection.getConnection();
try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new Fournisseur(
                    rs.getInt("id_fournisseur"),
                    rs.getString("nom"),
                    rs.getString("ville"),
                    rs.getString("telephone")
                ));
            }
        } catch (SQLException e) {
            System.out.println(" Erreur liste fournisseurs : " + e.getMessage());
        }
        return liste;
    }
}
