package com.example.acer.mynewponeapp.Model;

import android.content.Intent;

public class ProductModel {
    String name;
    double precio;
    String photoId;
    String description;
    int cantidad;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }



    public ProductModel()
    {

    }
    public ProductModel(String name, double precio, String description,int cantidad,String photoId ) {
        this.name = name;
        this.precio = precio;
        this.description = description;
        this.photoId=photoId;
        this.cantidad=cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getName() {
        return name;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getDescription() {
        return description;
    }


}
