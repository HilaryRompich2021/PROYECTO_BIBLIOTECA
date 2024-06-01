/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;

import ClasesJava.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class CrearCuentaClienteController implements Initializable {

    @FXML
    private AnchorPane anchorp;
    @FXML
    private ImageView imgLogo;
    @FXML
    private ImageView imgusuario;
    @FXML
    private Label LabelLogueo;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private TextField nombretxt;
    @FXML
    private TextField apellidotxt;
    @FXML
    private TextField direcciontxt;
    @FXML
    private TextField usuariotxt;
    @FXML
    private TextField telefonotxt;
    @FXML
    private TextField contraseñatxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void btnCrearCuenta(ActionEvent event){
     if (event.getSource() == btnCrearCuenta){
    agregarRegistro();
    try {
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
        
        // Obtener el Stage actual (de la ventana principal) y cerrarlo
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        
        // Muestra la nueva ventana
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    }
    // Invocando la conexion a la base de datos
    private static Connection conn = null;
    public CrearCuentaClienteController(){
     conn = Conexion.getConnection();
}
    
    
   //--------------------------------Arreglo-------------------------------------------------------------
   public ObservableList<Usuarios>getUsuarioslist(){
   
   ObservableList<Usuarios> usuariosList = FXCollections.observableArrayList();
   Connection conn = getConnection();
   String query = "SELECT * FROM usuarios";
   Statement st;
   ResultSet rs;
   
   try{
   st = conn.createStatement();
   rs = st.executeQuery(query);
   Usuarios usuarios;
   while(rs.next()){
       usuarios = new Usuarios(rs.getInt("idUsuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getInt("telefono"), rs.getString("usuario"), rs.getString("contraseña"));
       usuariosList.add(usuarios);
   }
   
   }catch(Exception ex){
       ex.printStackTrace();
   }
   return usuariosList;
   
   }
   
    //-------------------------------------- Agregar registro--------------------------------------------
   public void agregarRegistro(){
       if (usuarioExiste(usuariotxt.getText())) {
        mostrarAlertaUsuarioExistente();
    } else {
        // Vector para almacenar los datos del usuario
        Vector<Object> userData = new Vector<>();

        // Agrega los datos del usuario al vector
        userData.add(nombretxt.getText());
        userData.add(apellidotxt.getText());
        userData.add(direcciontxt.getText());
        userData.add(Integer.parseInt(telefonotxt.getText()));
        userData.add(usuariotxt.getText());
        userData.add(contraseñatxt.getText());

        String query = "INSERT INTO usuarios (nombre, apellido, direccion, telefono, usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Asigna los valores del vector al PreparedStatement
            for (int i = 0; i < userData.size(); i++) {
                Object data = userData.get(i);
                if (data instanceof String) {
                    pstmt.setString(i + 1, (String) data);
                } else if (data instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) data);
                }
            }

            pstmt.executeUpdate();
            limpiarCampos();
            mostrarAlertaCuentaCreada();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 /* if (usuarioExiste(usuariotxt.getText())) {
            mostrarAlertaUsuarioExistente();
        } else {
            String query = "INSERT INTO usuarios (nombre, apellido, direccion, telefono, usuario, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nombretxt.getText());
            pstmt.setString(2, apellidotxt.getText());
            pstmt.setString(3, direcciontxt.getText());
            pstmt.setInt(4, Integer.parseInt(telefonotxt.getText()));
            pstmt.setString(5, usuariotxt.getText());
            pstmt.setString(6, contraseñatxt.getText());

            pstmt.executeUpdate();
            limpiarCampos();
            mostrarAlertaCuentaCreada();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }*/
   }
   //--------------------------------Ejecutar Solicitud----------------------------------------
   public void ejecutarQuery(String query, Runnable callback){
   Connection conn = getConnection();
   Statement st;
   
    try {
        st = conn.createStatement();
        st.executeUpdate(query);
        callback.run(); // Llamar a la devolución de llamada después de ejecutar la consulta
           
        } catch (Exception ex) {
           // Logger.getLogger(UsuariosBD.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
        }
   }

    //------------------------------ limpiar field text----------------------------------------------------
   private void limpiarCampos() {
    nombretxt.setText("");
    apellidotxt.setText("");
    direcciontxt.setText("");
    telefonotxt.setText("");
    usuariotxt.setText("");
    contraseñatxt.setText("");
}
    //--------------------------------Mostrar Alerta----------------------------------------
    private void mostrarAlertaCuentaCreada() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cuenta creada");
        alert.setHeaderText(null);
        alert.setContentText("La cuenta ha sido creada exitosamente.");
        alert.showAndWait();
    }
    private void mostrarAlertaUsuarioExistente() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Usuario existente");
    alert.setHeaderText(null);
    alert.setContentText("El nombre de usuario ya existe. Por favor, elige otro nombre de usuario.");
    alert.showAndWait();
}
    public boolean usuarioExiste(String usuario) {
    String query = "SELECT * FROM usuarios WHERE usuario = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, usuario);

        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); // Devuelve true si se encontró un usuario con el mismo nombre
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return false; // Devuelve false si no se encontró ninguna coincidencia
}

}