/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.util;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author HP
 */


public class PasswordUtil {

    // Hasher le mot de passe avec salt automatique
    public static String hasher(String motDePasse) {
        return BCrypt.hashpw(motDePasse, BCrypt.gensalt(12));
    }

    // Vérifier mot de passe saisi vs hash stocké en base
    public static boolean verifier(String motDePasse, String hash) {
        return BCrypt.checkpw(motDePasse, hash);
    }
}
