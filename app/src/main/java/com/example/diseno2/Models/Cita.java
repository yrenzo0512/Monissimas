package com.example.diseno2.Models;

public class Cita {
    private String ID_USUARIO;
    private String aptDate;
    private String aptTime;
    private String ID_SERVICIO;
    private String observacion;

    public Cita(String ID_USUARIO, String aptDate, String aptTime, String ID_SERVICIO, String observacion) {
        this.ID_USUARIO = ID_USUARIO;
        this.aptDate = aptDate;
        this.aptTime = aptTime;
        this.ID_SERVICIO = ID_SERVICIO;
        this.observacion = observacion;
    }
    public String getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(String ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }
    public String getAptDate() {
        return aptDate;
    }
    public void setAptDate(String aptDate) {
        this.aptDate = aptDate;
    }
    public String getAptTime() {
        return aptTime;
    }
    public void setAptTime(String aptTime) {
        this.aptTime = aptTime;
    }
    public String getID_SERVICIO() {
        return ID_SERVICIO;
    }
    public void setID_SERVICIO(String ID_SERVICIO) {
        this.ID_SERVICIO = ID_SERVICIO;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

