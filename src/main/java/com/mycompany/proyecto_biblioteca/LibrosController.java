/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;

import ClasesJava.Libro;
import ClasesJava.Usuarios;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sql.Conexion;
import static sql.Conexion.getConnection;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class LibrosController implements Initializable {

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
    private TableView<Libro> TbLibros;
    @FXML
    private TableColumn<Libro, Integer> colidSerial;
    @FXML
    private TableColumn<Libro, String> colISBN;
    @FXML
    private TableColumn<Libro, String> coltitulo;
    @FXML
    private TableColumn<Libro, String> colautor;
    @FXML
    private TableColumn<Libro, String> coleditorial;
    @FXML
    private TableColumn<Libro, Integer> colcantidadDisponible;
    @FXML
    private TableColumn<Libro, String> coltipoDeLibro;
    @FXML
    private TableColumn<Libro, Integer> colañoDePublicacion;
    //private TextField idSerialtxt;
    @FXML
    private TextField ISBNtxt;
    @FXML
    private TextField titulotxt;
    @FXML
    private TextField autortxt;
    @FXML
    private TextField cantidadDisponibletxt;
    @FXML
    private TextField editorialtxt;
    @FXML
    private TextField tipoDeLibrotxt;
    @FXML
    private TextField añoDePublicaciontxt;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    
    private File selectedFile;
    @FXML
    private TextField rutaImagentxt;
    @FXML
    private ImageView imgLibroSeleccionado;
    @FXML
    private Label txtImagen;
    @FXML
    private Button btnSeleccionarImagen;
    @FXML
    private TableColumn<Libro, ImageView> colimagen;
    @FXML
    private Label verimagenlbel;
    @FXML
    private TextField idSerialtxt;

    /**
     * Initializes the controller class.
     */
    
    //sobreescritura (polimorfismo)
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarLibros();
        //------------ Cargar los datos a los field text al seleccionar un registro en la tabla---------------
         TbLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatos(newSelection);
            }
        });
         
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
   public LibrosController(){
     conn = Conexion.getConnection();
}
    
   //--------------------------------Arreglo-------------------------------------------------------------
   public ObservableList<Libro>getLibroslist(){
   
   ObservableList<Libro> LibrosList = FXCollections.observableArrayList();
   Connection conn = getConnection();
   String query = "SELECT * FROM libros";
   Statement st;
   ResultSet rs;
   
   try{
   st = conn.createStatement();
   rs = st.executeQuery(query);
   Libro libros;
   while(rs.next()){
        byte[] imageBytes = rs.getBytes("imagen");
            // Manejar el caso donde la imagen es null
            if (imageBytes == null) {
                imageBytes = new byte[0]; // O proporciona una imagen predeterminada
            }
     
       libros = new Libro(rs.getInt("idSerial"), rs.getString("ISBN"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getInt("cantidadDisponible"), rs.getString("tipoDeLibro"), rs.getInt("añoDePublicacion"), rs.getBytes("imagen"));
       LibrosList.add(libros);
   }
   
   }catch(Exception ex){
       ex.printStackTrace();
   }
   return LibrosList;
   
   }
   
   //-------------------------------------Metodo para mostrar en tabla---------------------------------
   public void mostrarLibros(){
   ObservableList<Libro> list = getLibroslist();
   colidSerial.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("idSerial"));
   colISBN.setCellValueFactory(new PropertyValueFactory<Libro, String>("ISBN"));
   coltitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
   colautor.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
   coleditorial.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
   colcantidadDisponible.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("cantidadDisponible"));
   coltipoDeLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("tipoDeLibro"));
   colañoDePublicacion.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("añoDePublicacion"));
   colimagen.setCellValueFactory(new PropertyValueFactory<Libro, ImageView>("imagen"));
   
   TbLibros.setItems(list);
   }
   
   //-------------------------------------- Agregar registro--------------------------------------------
   public void agregarRegistro(){
      
    // Verifica si se ha seleccionado una imagen
    if (selectedFile == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, seleccione una imagen.");
        alert.showAndWait();
        return;
    }

    // Consulta SQL para insertar un nuevo libro en la base de datos
    String query = "INSERT INTO libros (ISBN, titulo, autor, editorial, cantidadDisponible, tipoDeLibro, añoDePublicacion, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    // Vector para almacenar los datos del formulario y la imagen
    Vector<Object> libroData = new Vector<>();

    // Lee la imagen seleccionada y la convierte en un arreglo de bytes
    byte[] imageData = null;
    try (FileInputStream fis = new FileInputStream(selectedFile)) {
        imageData = new byte[(int) selectedFile.length()];
        if (fis.read(imageData) != imageData.length) {
            throw new IOException("No se pudo leer el archivo completo.");
        }
    } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Archivo");
        alert.setHeaderText(null);
        alert.setContentText("Ocurrió un error al leer la imagen seleccionada.");
        alert.showAndWait();
        return;
    }

    // Agrega los datos al vector
    libroData.add(ISBNtxt.getText());
    libroData.add(titulotxt.getText());
    libroData.add(autortxt.getText());
    libroData.add(editorialtxt.getText());
    libroData.add(Integer.parseInt(cantidadDisponibletxt.getText()));
    libroData.add(tipoDeLibrotxt.getText());
    libroData.add(Integer.parseInt(añoDePublicaciontxt.getText()));
    libroData.add(imageData);

    // Conecta con la base de datos y ejecuta la consulta de inserción
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // Asigna los valores del vector al PreparedStatement
        for (int i = 0; i < libroData.size(); i++) {
            Object data = libroData.get(i);
            if (data instanceof String) {
                pstmt.setString(i + 1, (String) data);
            } else if (data instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) data);
            } else if (data instanceof byte[]) {
                pstmt.setBytes(i + 1, (byte[]) data);
            }
        }

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Creado");
            alert.setHeaderText(null);
            alert.setContentText("El libro se ha registrado exitosamente.");
            alert.showAndWait();

            mostrarLibros();
            limpiarCampos();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Ocurrió un error al registrar el libro.");
        alert.showAndWait();
    }

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
           ex.printStackTrace();
        }
   }

    //--------------------------------Actualizar registro--------------------------------------------
   private void actualizarRegistro (){
   String query = "UPDATE libros SET ISBN = '" + ISBNtxt.getText() + "', titulo= '" + titulotxt.getText() +
   "', autor= '" + autortxt.getText() + "', editorial= '" + editorialtxt.getText() + "', cantidadDisponible= '" + cantidadDisponibletxt.getText() +
   "', tipoDeLibro= '" + tipoDeLibrotxt.getText() + "', añoDePublicacion= '"+ añoDePublicaciontxt.getText() + "' WHERE idSerial = " + idSerialtxt.getText();
   ejecutarQuery(query, () ->{
          // mostrarUsuarios();
        
           // Actualizar el registro en la lista observable
        Libro selectedLibro = TbLibros.getSelectionModel().getSelectedItem();
        if (selectedLibro != null) {
            selectedLibro.setISBN(ISBNtxt.getText());
            selectedLibro.setTitulo(titulotxt.getText());
            selectedLibro.setAutor(autortxt.getText());
            selectedLibro.setEditorial(editorialtxt.getText());
            selectedLibro.setCantidadDisponible(Integer.parseInt(cantidadDisponibletxt.getText()));
            selectedLibro.setTipoDeLibro(tipoDeLibrotxt.getText());
            selectedLibro.setAñoDePublicacion(Integer.parseInt(añoDePublicaciontxt.getText()));

            // Refrescar la tabla para mostrar los cambios
            TbLibros.refresh();
}
        // Limpiar los campos de texto
        limpiarCampos();
   });
   }
   
   //------------------------------------Eliminar registro---------------------------------------------
   private void eliminarRegistro(){
   Libro selectedLibro = TbLibros.getSelectionModel().getSelectedItem();
   if (selectedLibro != null) {
     int id = selectedLibro.getIdSerial();
   
   String query = "DELETE FROM libros WHERE idSerial =" + idSerialtxt.getText();
   ejecutarQuery(query, () ->{
   //actualizarIds();
   mostrarLibros();
   // Limpiar los campos de texto
        limpiarCampos();
   });
   
   }
   }
   
   //---------------------------------Seleccionar imagen-----------------------------------------------------
    @FXML
    public void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            rutaImagentxt.setText(selectedFile.getAbsolutePath());
             
        // Actualizar la imagen en el Label txtImagen
        Image image = new Image(selectedFile.toURI().toString());
        imgLibroSeleccionado.setImage(image);
        //txtImagen.setGraphic(new ImageView(image));
        }
    }
 

   //--------------------------------carga de datos al seleccionar un registro de la tabla----------------------
   private void cargarDatos(Libro libros) {
        idSerialtxt.setText(String.valueOf(libros.getIdSerial()));
        ISBNtxt.setText(libros.getISBN());
        titulotxt.setText(libros.getTitulo());
        autortxt.setText(libros.getAutor());
        editorialtxt.setText(libros.getEditorial());
        cantidadDisponibletxt.setText(String.valueOf(libros.getCantidadDisponible()));
        tipoDeLibrotxt.setText(libros.getTipoDeLibro());
        añoDePublicaciontxt.setText(String.valueOf(libros.getAñoDePublicacion()));
       //imgLibroSeleccionado.setImage(libros.getImagen().getImagen());
       // Cargar imagen en ImageView
    if (libros.getImagen() != null && libros.getImagen().length > 0) {
        ByteArrayInputStream bis = new ByteArrayInputStream(libros.getImagen());
        Image img = new Image(bis);
        imgLibroSeleccionado.setImage(img);
    } else {
        imgLibroSeleccionado.setImage(null); // O una imagen predeterminada si se prefiere
    }
        
    }
   
   //------------------------------ limpiar field text----------------------------------------------------
   private void limpiarCampos() {
    idSerialtxt.setText("");
    ISBNtxt.setText("");
    titulotxt.setText("");
    autortxt.setText("");
    editorialtxt.setText("");
    cantidadDisponibletxt.setText("");
    tipoDeLibrotxt.setText("");
    añoDePublicaciontxt.setText("");
    rutaImagentxt.setText("");
        selectedFile = null;
}

    
    
}
