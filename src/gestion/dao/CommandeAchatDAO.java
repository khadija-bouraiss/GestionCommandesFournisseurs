/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.CommandeAchat;
import java.util.List;


/**
 *
 * @author HP
 */

public interface CommandeAchatDAO {
    void create(CommandeAchat commande);
    void update(CommandeAchat commande);
    void delete(int id);
    CommandeAchat findById(int id);
    List<CommandeAchat> findAll();
    List<CommandeAchat> findByStatut(String statut);
    List<CommandeAchat> findByFournisseur(int idFournisseur);
    List<CommandeAchat> findByPeriode(String dateDebut, String dateFin);
    void validerLivraison(int idCommande);
}