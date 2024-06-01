/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesJava;

/**
 *
 * @author Hilar
 */
public class Usuarios {
private int idUsuario;
private String nombre;
private String apellido;
private String direccion;
private int telefono;
private String usuario;
private String contraseña;



   public Usuarios( int idUsuario, String nombre, String apellido, String direccion, int telefono, String usuario, String contraseña) {
        this.idUsuario= idUsuario;
        this.nombre = nombre;
        this.apellido=apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuario = usuario;
        this.contraseña = contraseña;
        
    }

    public Usuarios(){
    }

    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

 @Override
    public String toString() {
        return "Datos de usuario:Id Usuario:" + getIdUsuario() + "Nombre: " + getNombre() +  " Apellido " + getApellido() + " Direccion: " + getDireccion()  + " Telefono: " + getTelefono() + "Usuario:" + getUsuario() + "Contraseña:" + getContraseña();
    }

}
    