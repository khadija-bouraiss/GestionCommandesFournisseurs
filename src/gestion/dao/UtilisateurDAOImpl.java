/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.Utilisateur;
import gestion.util.DBConnection;
import gestion.util.PasswordUtil;
import java.sql.*;


/**
 *
 * @author HP
 */


public class UtilisateurDAOImpl implements UtilisateurDAO {

    private Connection conn = DBConnection.getConnection();

    @Override
    public void create(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (login, passwordHash) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getLogin());
            ps.setString(2, PasswordUtil.hasher(u.getPasswordHash()));
            ps.executeUpdate();
            System.out.println(" Utilisateur créé !");
        } catch (SQLException e) {
            System.out.println(" Erreur création utilisateur : " + e.getMessage());
        }
    }

    @Override
    public Utilisateur findByLogin(String login) {
        String sql = "SELECT * FROM utilisateur WHERE login=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("login"),
                    rs.getString("passwordHash")
                );
            }
        } catch (SQLException e) {
            System.out.println(" Erreur recherche : " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean authentifier(String login, String motDePasse) {
        Utilisateur u = findByLogin(login);
        if (u == null) return false;
        return PasswordUtil.verifier(motDePasse, u.getPasswordHash());
    }

    public boolean updateMotDePasseByEmail(String email, String newHash) {
    String sql = "UPDATE utilisateur SET passwordHash=? WHERE email=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, newHash);
        ps.setString(2, email);
        int rows = ps.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        System.out.println("Erreur update mot de passe : " + e.getMessage());
        return false;
    }
}
}