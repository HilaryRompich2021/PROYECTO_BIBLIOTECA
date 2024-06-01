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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sql.Conexion;
import static sql.Conexion.getConnection;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class UsuariosController implements Initializable {

    @FXML
    private ImageView btnAtrás;
    @FXML
    private ImageView imgUsuario;
    @FXML
    private Button btnLibros;
    @FXML
    private Button btnprestatarios;
    @FXML
    private Button btnLibrosPrestados;
    @FXML
    private Button btnVencidos;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnNotificaciones;
    @FXML
    private TableView<Usuarios> TbUsuarios;
    @FXML
    private TableColumn<Usuarios, Integer> colidUsuario;
    @FXML
    private TableColumn<Usuarios, String> colnombre;
    @FXML
    private TableColumn<Usuarios, String> colapellido;
    @FXML
    private TableColumn<Usuarios, String> coldireccion;
    @FXML
    private TableColumn<Usuarios, Integer> coltelefono;
    @FXML
    private TableColumn<Usuarios, String> colusuario;
    @FXML
    private TableColumn<Usuarios, String> colcontraseña;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField idUsuariotxt;
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
            mostrarUsuarios();
            
    //------------ Cargar los datos a los field text al seleccionar un registro en la tabla---------------
         TbUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatos(newSelection);
            }
        });
      

    }  
    
    //-----------------------------botón libros---------------------------------------
    
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
   /* @FXML
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
    }*/
    
    //----------------------botón agregar---------------------------------------------
    @FXML
    public void btnAgregar(ActionEvent event){
    if (event.getSource() == btnAgregar){
    agregarRegistro();
    }else if (event.getSource() == btnActualizar){
        actualizarRegistro();
    }else if(event.getSource() == btnEliminar){
        eliminarRegistro();
    }
    }
    
    private static Connection conn = null;
   
   //incovamos al método getConexion
   public UsuariosController(){
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
   //-------------------------------------Metodo para mostrar en tabla---------------------------------
   public void mostrarUsuarios(){
   ObservableList<Usuarios> list = getUsuarioslist();
   colidUsuario.setCellValueFactory(new PropertyValueFactory<Usuarios, Integer>("idUsuario"));
   colnombre.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("nombre"));
   colapellido.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("apellido"));
   coldireccion.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("direccion"));
   coltelefono.setCellValueFactory(new PropertyValueFactory<Usuarios, Integer>("telefono"));
   colusuario.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("usuario"));
   colcontraseña.setCellValueFactory(new PropertyValueFactory<Usuarios, String>("contraseña"));
   
   TbUsuarios.setItems(list);
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
            mostrarUsuarios();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*if (usuarioExiste(usuariotxt.getText())) {
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
            mostrarUsuarios();

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

//--------------------------------Actualizar registro--------------------------------------------
   private void actualizarRegistro (){
   String query = "UPDATE usuarios SET nombre = '" + nombretxt.getText() + "', apellido= '" + apellidotxt.getText() +
   "', direccion= '" + direcciontxt.getText() + "', telefono= '" + telefonotxt.getText() + "', usuario= '" + usuariotxt.getText() +
   "', contraseña= '" + contraseñatxt.getText() + "' WHERE idUsuario = " + idUsuariotxt.getText();
   ejecutarQuery(query, () ->{
          // mostrarUsuarios();
           
           // Actualizar el registro en la lista observable
        Usuarios selectedUsuario = TbUsuarios.getSelectionModel().getSelectedItem();
        if (selectedUsuario != null) {
            selectedUsuario.setNombre(nombretxt.getText());
            selectedUsuario.setApellido(apellidotxt.getText());
            selectedUsuario.setDireccion(direcciontxt.getText());
            selectedUsuario.setTelefono(Integer.parseInt(telefonotxt.getText()));
            selectedUsuario.setUsuario(usuariotxt.getText());
            selectedUsuario.setContraseña(contraseñatxt.getText());

            // Refrescar la tabla para mostrar los cambios
            TbUsuarios.refresh();
}
        // Limpiar los campos de texto
        limpiarCampos();
   });
   }
 //------------------------------------Eliminar registro---------------------------------------------
   private void eliminarRegistro(){
   Usuarios selectedUsuario = TbUsuarios.getSelectionModel().getSelectedItem();
   if (selectedUsuario != null) {
     int id = selectedUsuario.getIdUsuario();
   
   String query = "DELETE FROM usuarios WHERE idUsuario =" + idUsuariotxt.getText();
   ejecutarQuery(query, () ->{
   //actualizarIds();
   mostrarUsuarios();
   // Limpiar los campos de texto
        limpiarCampos();
   });
   
   }
   }
   //----------------------------------Actualizar ID después de eliminar-------------------------------------
   /*public void actualizarIds() {
    ObservableList<Usuarios> list = getUsuarioslist();
    int newId = 1;
    for (Usuarios usuario : list) {
        String query = "UPDATE usuarios SET idUsuario = " + newId + " WHERE idUsuario = " + usuario.getIdUsuario();
        ejecutarQuery(query, () -> {});
        newId++;
    }
}*/

   
//--------------------------------carga de datos al seleccionar un registro de la tabla----------------------
   private void cargarDatos(Usuarios usuario) {
        idUsuariotxt.setText(String.valueOf(usuario.getIdUsuario()));
        nombretxt.setText(usuario.getNombre());
        apellidotxt.setText(usuario.getApellido());
        direcciontxt.setText(usuario.getDireccion());
        telefonotxt.setText(String.valueOf(usuario.getTelefono()));
        usuariotxt.setText(usuario.getUsuario());
        contraseñatxt.setText(usuario.getContraseña());
    }
   
   //------------------------------ limpiar field text----------------------------------------------------
   private void limpiarCampos() {
    idUsuariotxt.setText("");
    nombretxt.setText("");
    apellidotxt.setText("");
    direcciontxt.setText("");
    telefonotxt.setText("");
    usuariotxt.setText("");
    contraseñatxt.setText("");
}
 //--------------------------------Mostrar Alerta----------------------------------------
    private void mostrarAlertaCuentaCreada() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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


