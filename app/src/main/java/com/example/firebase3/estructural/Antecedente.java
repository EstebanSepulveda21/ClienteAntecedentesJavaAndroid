package com.example.firebase3.estructural;

import java.util.Date;

public class Antecedente {

    public final static String COLLECTION = "antecedentes";

    private int id;
    private String ciudadanoDi;
    private int delitoCodigo;
    private String ciudad;
    private Date fechaDelito;
    private int sentencia;
    private String estado;

    public Antecedente() {
    }

    public Antecedente(int id, String ciudadanoDi, int delitoCodigo, String ciudad, Date fechaDelito, int sentencia, String estado) {
        this.id = id;
        this.ciudadanoDi = ciudadanoDi;
        this.delitoCodigo = delitoCodigo;
        this.ciudad = ciudad;
        this.fechaDelito = fechaDelito;
        this.sentencia = sentencia;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiudadanoDi() {
        return ciudadanoDi;
    }

    public void setCiudadanoDi(String ciudadanoDi) {
        this.ciudadanoDi = ciudadanoDi;
    }

    public int getDelitoCodigo() {
        return delitoCodigo;
    }

    public void setDelitoCodigo(int delitoCodigo) {
        this.delitoCodigo = delitoCodigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaDelito() {
        return fechaDelito;
    }

    public void setFechaDelito(Date fechaDelito) {
        this.fechaDelito = fechaDelito;
    }

    public int getSentencia() {
        return sentencia;
    }

    public void setSentencia(int sentencia) {
        this.sentencia = sentencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Antecedente{" +
                "id=" + id +
                ", ciudadanoDi='" + ciudadanoDi + '\'' +
                ", delitoCodigo=" + delitoCodigo +
                ", ciudad='" + ciudad + '\'' +
                ", fechaDelito=" + fechaDelito +
                ", sentencia=" + sentencia +
                ", estado='" + estado + '\'' +
                '}';
    }
}
