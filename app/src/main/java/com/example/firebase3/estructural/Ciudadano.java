package com.example.firebase3.estructural;

import java.util.Date;

public class Ciudadano {
    public final static String COLLECTION = "ciudadanos";
    private String cedula;
    private Date fecha;
    private int tipoDocumento;
    private String nombre;
    private String apellido;

    public Ciudadano() {
    }

    public Ciudadano(String cedula, Date fecha, int tipoDocumento, String nombre, String apellido) {
        this.cedula = cedula;
        this.fecha = fecha;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
    }



    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "cedula='" + cedula + '\'' +
                ", fecha=" + fecha +
                ", tipoDocumento=" + tipoDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
