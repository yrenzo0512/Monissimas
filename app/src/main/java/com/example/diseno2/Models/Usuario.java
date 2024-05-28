package com.example.diseno2.Models;

public class Usuario {
    private String ID_USUARIO;
    private String nombres;
    private String correo;
    private String telefono;
    private String dni;
    private String user;
    private String password;

    public Usuario(String ID_USUARIO, String nombres, String correo, String telefono, String dni, String user, String password) {
        this.ID_USUARIO = ID_USUARIO;
        this.nombres = nombres;
        this.correo = correo;
        this.telefono = telefono;
        this.dni = dni;
        this.user = user;
        this.password = password;
    }

    public String getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(String ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

