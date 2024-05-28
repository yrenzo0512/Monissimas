package com.example.diseno2.Models;

public class Servicio {

    private String ID_SERVICIO;
    private String nombreservicio;
    private String precioservicio;
    private String descripciones;
    private String imagen;

    public Servicio(String ID_SERVICIO, String nombreservicio, String precioservicio, String descripciones, String imagen) {
        this.ID_SERVICIO = ID_SERVICIO;
        this.nombreservicio = nombreservicio;
        this.precioservicio = precioservicio;
        this.descripciones = descripciones;
        this.imagen = imagen;
    }

    public String getID_SERVICIO() {
        return ID_SERVICIO;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public String getPrecioservicio() {
        return precioservicio;
    }

    public String getDescripciones() {
        return descripciones;
    }

    public String getImagen() {
        return imagen;
    }
}
