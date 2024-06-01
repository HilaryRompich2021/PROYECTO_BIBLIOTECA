/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesJava;

/**
 *
 * @author Hilar
 */
public class Cliente extends Usuarios{
    //int IdCliente;
    boolean prestamosActivos; 

    public Cliente(int idUsuario, int IdCliente, boolean prestamosActivos, String nombre, String apellido, String direccion, int telefono, String usuario, String contraseña) {
        super(idUsuario,nombre, apellido, direccion, telefono, usuario, contraseña);
       // this.IdCliente = IdCliente;
        this.prestamosActivos = false;
    }
    
     
    
    
}
