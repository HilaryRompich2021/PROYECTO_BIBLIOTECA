/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesJava;

import javafx.scene.image.ImageView;

/**
 *
 * @author Hilar
 */
public class Libro {
    //Abstracción son los atributos
   private int idSerial;
   private String ISBN;
   private String titulo;
   private String autor;
   private String editorial;
   private int cantidadDisponible;    
   private String tipoDelibro;
   private int añoDepublicacion;
   private byte[] imagen;

   //Encapsulamiento lo privado y publico de las clases, por medio del constructor utilizo los atributos privados
public Libro( int idSerial, String ISBN, String titulo, String autor, String editorial,int cantidadDisponible, String tipoDelibro,  int añoDePublicacion,  byte[] imagen){
       this.idSerial = idSerial;
       this.titulo = titulo;
       this.autor = autor;
       this.editorial = editorial;
       this.cantidadDisponible = cantidadDisponible;
       this.tipoDelibro = tipoDelibro;
       this.ISBN = ISBN;
       this.añoDepublicacion = añoDePublicacion;
       this.imagen = (imagen != null) ? imagen : new byte[0]; // Maneja el caso de imagen nula
   }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen( byte[] imagen) {
        this.imagen = imagen;
    }

   public int getIdSerial(){
       return idSerial;
   }
   
   public void setIdSerial(int idSerial){
       this.idSerial = idSerial;
   }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getTipoDeLibro() {
        return tipoDelibro;
    }

    public void setTipoDeLibro(String tipoDelibro) {
        this.tipoDelibro = tipoDelibro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getAñoDePublicacion() {
        return añoDepublicacion;
    }

    public void setAñoDePublicacion(int añoDepublicacion) {
        this.añoDepublicacion = añoDepublicacion;
    }
   
   @Override
    public String toString() {
        return "Datos de libro:Idserial:" + getIdSerial() + "Título: " + getTitulo() + " Autor: " + getAutor() +  " Editorial: " + getEditorial() + " Cantidad Disponible: " + getCantidadDisponible() + " Tipo de Libro: " + getTipoDeLibro() + "ISBN:" + getISBN() + "Año de Publicación:" + getAñoDePublicacion() + "imagen:" + getImagen();
    }
}