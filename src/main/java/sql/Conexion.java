/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;
//import java.sql.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hilar
 */
public class Conexion {
    private static final String URL = "jdbc:postgresql://dpg-coul857sc6pc73amfl00-a.oregon-postgres.render.com/proyectobiblioteca";
    private static final String USER = "hilary";
    private static final String PASSWORD = "52xZNM5UQKksoawSiMpz0lrwCHAPCBYz";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
}
