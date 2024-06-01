/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesJava;

import java.time.LocalDate;

/**
 *
 * @author Hilar
 */
public class Transaccion {
    
    private int idTransaccion;
    private String prestarLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    private Estado estado;
    private int montoMulta;
    private int idUsuario;
    private int idSerial;

    public Transaccion(int idTransaccion, String prestarLibro, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista, LocalDate fechaDevolucionReal, Estado estado, int montoMulta) {
        this.idTransaccion = idTransaccion;
        this.prestarLibro = prestarLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
        this.montoMulta = montoMulta;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getPrestarLibro() {
        return prestarLibro;
    }

    public void setPrestarLibro(String prestarLibro) {
        this.prestarLibro = prestarLibro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getMontoMulta() {
        return montoMulta;
    }

    public void setMontoMulta(int montoMulta) {
        this.montoMulta = montoMulta;
    }
    


//-------------- Estado----------------
public enum Estado {
    ACTIVO,
    INACTIVO,
    PENDIENTE
}
}