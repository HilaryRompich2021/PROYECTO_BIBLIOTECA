/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_biblioteca;


import Metodos.SeguridadAdmin;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Hilar
 */
public class LoginAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
   
    @FXML private Label LabelLogueo;
    @FXML private TextField txtuser;
    @FXML private PasswordField txtpwd;
    @FXML private ImageView imgusuario;
    @FXML private ImageView imgLogo;
    @FXML private AnchorPane anchorp;
    @FXML
    private Button btn_ingresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private static int intentos;
    private static String user,pwd;
    
    @FXML
    public void Click_Ingresar(javafx.event.ActionEvent event) throws IOException  {
        FileReader fr=null;
        try {
            int nLineas=0;
            int i=0;
            String [] usuarios = null;
            String linea;
            Scanner sc = new Scanner(new File ("C:\\Users\\Hilar\\Documents\\NetBeansProjects\\Proyecto_Biblioteca\\src\\main\\java\\Usuario.txt"));
            File f = new File ("C:\\Users\\Hilar\\Documents\\NetBeansProjects\\Proyecto_Biblioteca\\src\\main\\java\\Usuario.txt");
            fr = new FileReader (f);
             try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            
            
                while ((linea=br.readLine())!=null){
                    nLineas++;
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null,ex);
            }
            usuarios = new String [nLineas++];
            while (sc.hasNextLine()){
            usuarios [i++] = sc.nextLine();
            }
            intentos++;
                user = txtuser.getText();
                pwd = txtpwd.getText();
                
                SeguridadAdmin s = new SeguridadAdmin ();
                
               
                s.ValidarUsuario (usuarios,user,pwd,intentos);  
        } catch (FileNotFoundException ex){
            Logger.getLogger(LoginAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Click_Cancelar(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void Usuario(javafx.event.ActionEvent event) {
    }

    @FXML
    public void password(javafx.event.ActionEvent event) {
    }
    
    
    
   
}