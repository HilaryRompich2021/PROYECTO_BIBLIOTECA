/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;
import ClasesJava.Libro;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sql.Conexion;
import static sql.Conexion.getConnection;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class MenuClientesController implements Initializable {

    @FXML
    private ImageView btnAtrás;
    @FXML
    private ImageView imgUsuario;
    @FXML
    private Button btnmisprestamos;
    @FXML
    private Button btnNotificaciones;
    @FXML
    private TableView<Libro> TbLibros;
    @FXML
    private TableColumn<Libro, String> coltitulo;
    @FXML
    private TableColumn<Libro, String> colautor;
    @FXML
    private TableColumn<Libro, String> coltipoDeLibro;
    @FXML
    private ImageView imgLibroSeleccionado;
    @FXML
    private Button tbnPrestarLibro;
    @FXML
    private TextField ISBNtxt;
    @FXML
    private TextField titulotxt;
    @FXML
    private TextField autortxt;
    @FXML
    private TextField editorialtxt;
    @FXML
    private TextField tipoDeLibrotxt;
    @FXML
    private TextField añoDePublicaciontxt;
    @FXML
    private TextField busquedatxt;
    @FXML
    private Button btnBuscar;
    private ObservableList<Libro> libros;
    @FXML
    private TextField txtusuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        libros = getLibroslist();
        mostrarLibros(libros);
        mostrarLibrosN(libros);
        //------------ Cargar los datos a los field text al seleccionar un registro en la tabla---------------
         TbLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatos(newSelection);
            }
        });
         
         
    }    
    
     //-----------------------------botón libros---------------------------------------
    @FXML
    public void Click_tbnPrestarLibro(javafx.event.ActionEvent event) throws IOException  {
      PrestarLibro();
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
   public void mostrarLibros(ObservableList<Libro> listaLibros){
   ObservableList<Libro> list = getLibroslist();
   coltitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
   colautor.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
   coltipoDeLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("tipoDeLibro"));
  
   
   TbLibros.setItems(list);
   }
   public void mostrarLibrosN(ObservableList<Libro> listaLibros) {
    TbLibros.getItems().clear(); // Limpiar la tabla antes de añadir los nuevos datos
    TbLibros.setItems(listaLibros); // Establecer los nuevos datos en la tabla
}
   //--------------------------------carga de datos al seleccionar un registro de la tabla----------------------
   private void cargarDatos(Libro libros) {
        ISBNtxt.setText(libros.getISBN());
        titulotxt.setText(libros.getTitulo());
        autortxt.setText(libros.getAutor());
        editorialtxt.setText(libros.getEditorial());
        tipoDeLibrotxt.setText(libros.getTipoDeLibro());
        añoDePublicaciontxt.setText(String.valueOf(libros.getAñoDePublicacion()));
       
    if (libros.getImagen() != null && libros.getImagen().length > 0) {
        ByteArrayInputStream bis = new ByteArrayInputStream(libros.getImagen());
        Image img = new Image(bis);
        imgLibroSeleccionado.setImage(img);
    } else {
        imgLibroSeleccionado.setImage(null); // O una imagen predeterminada si se prefiere
    }
}
   
   //---------------------------- bucar libro ---------------------------------------------
   @FXML
public void manejarBuscar(ActionEvent e) {
    String textoBusqueda = busquedatxt.getText().toLowerCase();
    System.out.println("Texto de búsqueda: " + textoBusqueda);
    
    ObservableList<Libro> resultadosBusqueda = buscarLibro(textoBusqueda);

    if (resultadosBusqueda.isEmpty()) {
        mostrarAlerta("Sin resultados", "No se encontraron libros que coincidan con la búsqueda.");
    } else {
        System.out.println("Resultados encontrados: " + resultadosBusqueda.size());
        for (Libro libro : resultadosBusqueda) {
            System.out.println(libro.getTitulo() + " - " + libro.getAutor());
        }
    }

    // Actualizar el TableView con los resultados de la búsqueda
    mostrarLibrosN(resultadosBusqueda);
}
// Método para mostrar alertas
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();

    //-------------------------------------Método para buscar libro en la base de datos---------------------------------
   
}

   public ObservableList<Libro> buscarLibro(String textoBusqueda) {
        ObservableList<Libro> LibrosList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        //String query = "SELECT * FROM libros WHERE titulo = ? OR autor = ?";
        String query = "SELECT * FROM libros WHERE autor ILIKE ? OR titulo ILIKE ?";
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = conn.prepareStatement(query);
            String searchPattern = "%" + textoBusqueda + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
           // pst.setString(3, searchPattern);
            rs = pst.executeQuery();

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("imagen");
                // Manejar el caso donde la imagen es null
                if (imageBytes == null) {
                    imageBytes = new byte[0]; // O proporciona una imagen predeterminada
                }

                Libro libro = new Libro(rs.getInt("idSerial"), rs.getString("ISBN"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getInt("cantidadDisponible"), rs.getString("tipoDeLibro"), rs.getInt("añoDePublicacion"), rs.getBytes("imagen"));
                LibrosList.add(libro);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibrosList;
    }
   
   //--------------------------------------Prestar Libro-----------------------------------------------
   private void PrestarLibro() {
       try (Connection connection = Conexion.getConnection()) {
        String username = txtusuario.getText();
        Libro selectedBook = TbLibros.getSelectionModel().getSelectedItem();

        if (selectedBook != null && selectedBook.getCantidadDisponible() > 0) {
            // Verificar si el usuario tiene préstamos pendientes
            if (usuarioTienePrestamosPendientes(username)) {
                mostrarAlerta("Préstamo no aprobado", "El usuario tiene préstamos pendientes.");
                return; // Salir del método si el usuario tiene préstamos pendientes
            }

            // Obtener el ID del cliente a partir del nombre de usuario
            String getClientIdSQL = "SELECT idUsuario FROM usuarios WHERE usuario = ?";
            PreparedStatement getidUsuario = connection.prepareStatement(getClientIdSQL);
            getidUsuario.setString(1, username);
            ResultSet rs = getidUsuario.executeQuery();

            //if (clientId != -1) {
            if (rs.next()) {
                int clientId = rs.getInt("idUsuario");
                // Calcular la fecha de devolución prevista (14 días después del préstamo)
                LocalDate fechaPrestamo = LocalDate.now();
                LocalDate fechaDevolucionPrevista = fechaPrestamo.plusDays(14);

                // Actualizar la cantidad disponible del libro
                String updateLibrosSQL = "UPDATE libros SET cantidadDisponible = cantidadDisponible - 1 WHERE idSerial = ?";
                try (PreparedStatement updateLibrosStmt = connection.prepareStatement(updateLibrosSQL)) {
                    updateLibrosStmt.setInt(1, selectedBook.getIdSerial());
                    updateLibrosStmt.executeUpdate();
                }

                // Insertar la nueva transacción con estado 'prestado'
                String insertTransaccionSQL = "INSERT INTO transacciones (idUsuario, idSerial, fechaprestamo, fechaDevolucionPrevista, estado) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertTransaccionStmt = connection.prepareStatement(insertTransaccionSQL)) {
                    insertTransaccionStmt.setInt(1, clientId);
                    insertTransaccionStmt.setInt(2, selectedBook.getIdSerial());
                    insertTransaccionStmt.setDate(3, java.sql.Date.valueOf(fechaPrestamo));
                    insertTransaccionStmt.setDate(4, java.sql.Date.valueOf(fechaDevolucionPrevista));
                    insertTransaccionStmt.setString(5, "prestado");
                    insertTransaccionStmt.executeUpdate();
                }

                // Registrar las fechas de préstamo y devolución en matrices
                registrarFechasEnMatriz(selectedBook, fechaPrestamo, fechaDevolucionPrevista);

                // Actualizar la vista de la tabla
                selectedBook.setCantidadDisponible(selectedBook.getCantidadDisponible() - 1);
                TbLibros.refresh();

                mostrarAlerta("Préstamo exitoso", "El libro ha sido prestado correctamente.");
            } else {
                mostrarAlerta("Usuario no encontrado", "No se encontró un usuario con el nombre especificado.");
            }
        } else {
            mostrarAlerta("Libro no disponible", "El libro seleccionado no está disponible para préstamo.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        mostrarAlerta("Error", "Ocurrió un error al realizar el préstamo.");
    }
}

private void registrarFechasEnMatriz(Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista) {
    // Obtener las fechas de préstamo y devolución prevista
    int idSerial = libro.getIdSerial();
    String titulo = libro.getTitulo();
    LocalDate[][] matrizFechas = new LocalDate[idSerial][2]; // Una matriz para cada libro, con dos columnas (fecha de préstamo y devolución prevista)
    matrizFechas[idSerial - 1][0] = fechaPrestamo;
    matrizFechas[idSerial - 1][1] = fechaDevolucionPrevista;

    // Aquí podrías hacer lo que necesites con la matriz de fechas, como almacenarla en una estructura de datos o imprimirla
    System.out.println("Fechas para el libro '" + titulo + "':");
    System.out.println("Fecha de préstamo: " + matrizFechas[idSerial - 1][0]);
    System.out.println("Fecha de devolución prevista: " + matrizFechas[idSerial - 1][1]);
}

private boolean usuarioTienePrestamosPendientes(String username) throws SQLException {
    String getUserTransactionsSQL = "SELECT COUNT(*) AS count FROM transacciones WHERE idUsuario = (SELECT idUsuario FROM usuarios WHERE usuario = ?) AND estado = ? AND fechaDevolucionReal IS NULL";
    try (Connection connection = Conexion.getConnection();
         PreparedStatement statement = connection.prepareStatement(getUserTransactionsSQL)) {
        statement.setString(1, username);
        statement.setString(2, "prestado");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        }
    }
    return false;
}
   }
     



