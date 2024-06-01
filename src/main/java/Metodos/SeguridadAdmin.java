/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos;

import com.mycompany.proyecto_biblioteca.MenuAdminController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 *
 * @author Hilar
 */

public class SeguridadAdmin {String res;

    public void ValidarUsuario(String [] usuarios,String user, String pwd, int intentos) {
       String linea = null;

       String[] valores = null;
 
       BufferedReader br = null;
    try {
        // Crea un objeto BufferedReader para leer el archivo
        br = new BufferedReader(new FileReader("C:\\Users\\Hilar\\Documents\\NetBeansProjects\\Proyecto_Biblioteca\\src\\main\\java\\Usuario.txt"));
        
        // Lee cada línea del archivo
        while ((linea = br.readLine()) != null) {
            // Divide la línea en dos valores utilizando una coma como separador
            valores = linea.split(",");
            if (valores.length == 2) {
                // Imprime los valores
                System.out.println("Valor 1: " + valores[0].trim());
                System.out.println("Valor 2: " + valores[1].trim());
            } else {
                System.out.println("El archivo no tiene el formato esperado.");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }    
        
         boolean encontrado = false;
        
        for (int i=0; i < valores.length;i++) {
            // Obtener el Stage actual (de la ventana principal) y cerrarlo
           
             if (valores[i].equalsIgnoreCase(user)&& valores [i+1].equals(pwd)){
             res = "Bienvenido" + user;
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inicio Sesión");
                alert.setHeaderText(null);
                alert.setContentText(res);
                alert.showAndWait();
      
        intentos = 0;
    
    FXMLLoader loader = new FXMLLoader();
    URL fxmlLocation = getClass().getResource("/com/mycompany/proyecto_biblioteca/MenuAdmin.fxml");
    if (fxmlLocation == null) {
    throw new RuntimeException("No se pudo encontrar el archivo FXML.");
    }
    loader.setLocation(fxmlLocation);
     try{    

    Parent root = loader.load();  // Carga el archivo FXML.
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Administración");
    stage.show();
     
} catch (IOException e) {
    e.printStackTrace();  // Imprime la pila de errores si algo sale mal
}

      
    }
    }
        if (encontrado==false) {
            res= "clave y/o usuarios erroneos con"+ intentos+"intentos fallidos";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inicio Sesión");
            alert.setHeaderText(null);
            alert.setContentText(res);
            alert.showAndWait();
        }
        if (intentos>2){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inicio Sesión");
            alert.setHeaderText(null);
            alert.setContentText(res);
            alert.showAndWait();
            System.exit(0);
            System.exit(0);
        }
    }
    
    
}
