/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.Fournisseur;
import java.util.List;

/**
 *
 * @author HP
 */

public interface FournisseurDAO {
    void create(Fournisseur fournisseur);
    void update(Fournisseur fournisseur);
    void delete(int id);
    Fournisseur findById(int id);
    List<Fournisseur> findAll();
}