/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.model;
import gestion.util.PasswordUtil;

/**
 *
 * @author HP
 */

public class Utilisateur {
    
    private int idUtilisateur;
    private String login;
    private String passwordHash;

    // Constructeur vide
    public Utilisateur() {}

    // Constructeur avec paramètres
    public Utilisateur(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    // Constructeur complet
    public Utilisateur(int idUtilisateur, String login, String passwordHash) {
        this.idUtilisateur = idUtilisateur;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    // Getters
    public int getIdUtilisateur() { return idUtilisateur; }
    public String getLogin() { return login; }
    public String getPasswordHash() { return passwordHash; }

    // Setters
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public void setLogin(String login) { this.login = login; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    // Méthode métier , hachage password 
    public boolean verifierMotDePasse(String motDePasse) {
    return PasswordUtil.verifier(motDePasse, this.passwordHash);
}

    @Override
    public String toString() {
        return login;
    }
}
