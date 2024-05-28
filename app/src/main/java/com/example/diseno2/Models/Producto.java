package com.example.diseno2.Models;

import java.io.Serializable;

public class Producto implements Serializable {
    private String ID_PRODUCTO;
    private String nombreproducto;
    private String descripciones;
    private Double precionormal;
    private String preciorebajado;
    private String stoks;
    private String imagen;
    private String ID_CATEGORIA;
    private int numberInCart;

    public Producto(String ID_PRODUCTO, String nombreproducto, String descripciones, Double precionormal, String preciorebajado, String stoks, String imagen, String ID_CATEGORIA) {
        this.ID_PRODUCTO = ID_PRODUCTO;
        this.nombreproducto = nombreproducto;
        this.descripciones = descripciones;
        this.precionormal = precionormal;
        this.preciorebajado = preciorebajado;
        this.stoks = stoks;
        this.imagen = imagen;
        this.ID_CATEGORIA = ID_CATEGORIA;
    }
    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
    public String getID_PRODUCTO() {
        return ID_PRODUCTO;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public String getDescripciones() {
        return descripciones;
    }

    public Double getPrecionormal() {
        return precionormal;
    }

    public String getPreciorebajado() {
        return preciorebajado;
    }

    public String getStoks() {
        return stoks;
    }

    public String getImagen() {
        return imagen;
    }

    public String getID_CATEGORIA() {
        return ID_CATEGORIA;
    }
}

