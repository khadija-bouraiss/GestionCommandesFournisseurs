/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/CommandeFournisseur";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" Connexion réussie !");
            }
        } catch (ClassNotFoundException e) {
            System.out.println(" Driver introuvable : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println(" Erreur connexion : " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" Connexion fermée !");
            }
        } catch (SQLException e) {
            System.out.println(" Erreur fermeture : " + e.getMessage());
        }
    }

    public static Object getInstance() {
        return null ; 
      }
}