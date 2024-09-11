package com.improvingskills.jdbc;

public class Producto {
    private String codigoArticulo;
    private String nombreArticulo;
    private String seccion;
    private Double precio;
    private String paisDeOrigen;
    private String fecha;
    private String importado;

    public Producto(String codigoArticulo, String nombreArticulo, String seccion, Double precio, String paisDeOrigen, String fecha, String importado) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.seccion = seccion;
        this.precio = precio;
        this.paisDeOrigen = paisDeOrigen;
        this.fecha = fecha;
        this.importado = importado;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImportado() {
        return importado;
    }

    public void setImportado(String importado) {
        this.importado = importado;
    }
}
