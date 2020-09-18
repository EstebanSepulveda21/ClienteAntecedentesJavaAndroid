package com.example.firebase3.estructural;

public class TipoDocumento {
    public final static String COLLECTION = "tipoDocumentos";
    private int codigo;
    private String siglas;
    private String descripcion;

    public TipoDocumento() {
    }

    public TipoDocumento(int codigo, String siglas, String descripcion) {
        this.codigo = codigo;
        this.siglas = siglas;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
                "codigo=" + codigo +
                ", siglas='" + siglas + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
