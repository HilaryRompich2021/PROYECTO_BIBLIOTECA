/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class LibrosPrestadosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML private Button btnLibros;
 @FXML private Button btnprestatarios;
 @FXML private Button btnLibrosPrestados;
 @FXML private Button btnVencidos;
 @FXML private Button btnUsuarios;
 @FXML private Button btnNotificaciones;
 @FXML private ImageView imgUsuario;
}
