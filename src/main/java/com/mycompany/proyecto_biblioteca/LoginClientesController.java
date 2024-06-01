/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sql.Conexion;
import static sql.Conexion.getConnection;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class LoginClientesController implements Initializable {

    @FXML
    private AnchorPane anchorp;
    @FXML
    private ImageView imgLogo;
    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpwd;
    @FXML
    private Button btn_ingresar;
    @FXML
    private ImageView imgusuario;
    @FXML
    private Label LabelLogueo;
    @FXML
    private Button btnCrearCuenta;
private Parent menuClientesRoot;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/MenuClientes.fxml"));
        menuClientesRoot = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }    
    
    @FXML
    public void btnCrearCuenta(ActionEvent event)throws IOException{
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyecto_biblioteca/CrearCuentaCliente.fxml"));
        Parent root = loader.load();
        
        // Obtén el controlador de la nueva ventana
        CrearCuentaClienteController controller = loader.getController();
        
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
    // Ingreso de usuario
    public boolean autenticarUsuario(String usuario, String contraseña) {
    String query = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, usuario);
        pstmt.setString(2, contraseña);

        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Devuelve true si se encontró un registro que coincide
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return false; // Devuelve false si no se encontró ninguna coincidencia
}


    @FXML
    private void Usuario(ActionEvent event) {
    }

    @FXML
    private void password(ActionEvent event) {
    }

    @FXML
    
private void Click_Ingresar(ActionEvent event) {
   String usuario = txtuser.getText();
    String contrasena = txtpwd.getText();

    if (autenticarUsuario(usuario, contrasena)) {
        mostrarAlertaLoginExitoso();
        Platform.runLater(() -> {
            try {
                // Crea una nueva escena con la ventana cargada
                Scene scene = new Scene(menuClientesRoot);
                
                // Crea un nuevo escenario y establece la escena
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("MenuClientes");
                
                // Muestra la nueva ventana
                stage.show();
                
                // Obtener el Stage actual (de la ventana principal) y cerrarlo
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    } else {
        mostrarAlertaLoginFallido();
    }
}

    
    private void mostrarAlertaLoginExitoso() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Inicio de sesión exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenido, " + txtuser.getText() + "!");
        alert.showAndWait();
    }

    private void mostrarAlertaLoginFallido() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Inicio de sesión fallido");
        alert.setHeaderText(null);
        alert.setContentText("El usuario o la contraseña son incorrectos. Por favor, inténtalo de nuevo.");
        alert.showAndWait();
    }
}
