/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.dao;
import gestion.model.Produit;
import java.util.List;

/**
 *
 * @author HP
 */

public interface ProduitDAO {
    void create(Produit produit);
    void update(Produit produit);
    void delete(int id);
    Produit findById(int id);
    List<Produit> findAll();
}
