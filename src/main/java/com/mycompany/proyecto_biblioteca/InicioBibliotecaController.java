/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class InicioBibliotecaController implements Initializable {

    @FXML
    private AnchorPane anchorp;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button Click_tbnCliente;
    @FXML
    private ImageView imgusuario;
    @FXML
    private Button Click_tbnAdministrador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    public void Click_tbnLoginCliente(javafx.event.ActionEvent event) throws IOException  {
        long startTime = System.currentTimeMillis();
    mostrarPantallaDeCarga();

    new Thread(() -> {
        try {
            long loadStartTime = System.currentTimeMillis();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/LoginClientes.fxml"));
            Parent root = loader.load();
            long loadEndTime = System.currentTimeMillis();

            // Obtener el controlador de la nueva ventana
            LoginClientesController controller = loader.getController();

            // Crear una nueva escena con la ventana cargada
            Scene scene = new Scene(root);

            Platform.runLater(() -> {
                try {
                    // Crear un nuevo escenario y establecer la escena
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Logueo de clientes");

                    // Obtener el Stage actual (de la ventana principal) y cerrarlo
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    // Mostrar la nueva ventana
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    ocultarPantallaDeCarga();
                }

                long endTime = System.currentTimeMillis();
                System.out.println("FXML Load Time: " + (loadEndTime - loadStartTime) + " ms");
                System.out.println("Total Time: " + (endTime - startTime) + " ms");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }).start();
}

private Stage loadingStage;

private void mostrarPantallaDeCarga() {
    Platform.runLater(() -> {
        if (loadingStage == null) {
            loadingStage = new Stage();
            ProgressIndicator progressIndicator = new ProgressIndicator();
            StackPane stackPane = new StackPane(progressIndicator);
            Scene scene = new Scene(stackPane, 200, 200);
            loadingStage.setScene(scene);
            loadingStage.initModality(Modality.APPLICATION_MODAL);
            loadingStage.initStyle(StageStyle.UNDECORATED);
        }
        loadingStage.show();
    });
}

private void ocultarPantallaDeCarga() {
    Platform.runLater(() -> {
        if (loadingStage != null) {
            loadingStage.hide();
        }
    });
       /* try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/LoginClientes.fxml"));
        Parent root = loader.load();
        
        // Obtén el controlador de la nueva ventana
        LoginClientesController controller = loader.getController();
        
        // Crea una nueva escena con la ventana cargada
        Scene scene = new Scene(root);
        
        // Crea un nuevo escenario y establece la escena
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Logueo de clientes");
        
        // Muestra la nueva ventana
        stage.show();
         // Obtener el Stage actual (de la ventana principal) y cerrarlo
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }*/
    }
    
     @FXML
    public void Click_tbnAdministrador(javafx.event.ActionEvent event) throws IOException  {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/LoginAdmin.fxml"));
        Parent root = loader.load();
        
        // Obtén el controlador de la nueva ventana
        LoginAdminController controller = loader.getController();
        
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
