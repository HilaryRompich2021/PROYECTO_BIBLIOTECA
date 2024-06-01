/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class MenuAdminController implements Initializable {

    @FXML
    private ImageView btnAtrás;
     @FXML private Button btnLibros;
 @FXML private Button btnprestatarios;
 @FXML private Button btnLibrosPrestados;
 @FXML private Button btnVencidos;
 @FXML private Button btnUsuarios;
 @FXML private Button btnNotificaciones;
 @FXML private ImageView imgUsuario;
    @FXML
    private Label txtTitulo;
    @FXML
    private Label txttexto1;
    @FXML
    private Label txttexto2;
    @FXML
    private Label txttexto3;
    @FXML
    private Label txttexto4;
    @FXML
    private ImageView imgFlores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    public void Click_tbnUsuarios(javafx.event.ActionEvent event) throws IOException  {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/Usuarios.fxml"));
        Parent root = loader.load();
        
        // Obtén el controlador de la nueva ventana
        UsuariosController controller = loader.getController();
        
        // Crea una nueva escena con la ventana cargada
        Scene scene = new Scene(root);
        
        // Crea un nuevo escenario y establece la escena
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Usuarios");
        
        // Muestra la nueva ventana
        stage.show();
         // Obtener el Stage actual (de la ventana principal) y cerrarlo
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    @FXML
    public void Click_tbnLibros(javafx.event.ActionEvent event) throws IOException  {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/Libros.fxml"));
        Parent root = loader.load();
        
        // Obtén el controlador de la nueva ventana
        LibrosController controller = loader.getController();
        
        // Crea una nueva escena con la ventana cargada
        Scene scene = new Scene(root);
        
        // Crea un nuevo escenario y establece la escena
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Libros");
        
        // Muestra la nueva ventana
        stage.show();
         // Obtener el Stage actual (de la ventana principal) y cerrarlo
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
