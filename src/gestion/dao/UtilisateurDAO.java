/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.Utilisateur;

/**
 *
 * @author HP
 */

public interface UtilisateurDAO {
    void create(Utilisateur utilisateur);
    Utilisateur findByLogin(String login);
    boolean authentifier(String login, String motDePasse);
}
