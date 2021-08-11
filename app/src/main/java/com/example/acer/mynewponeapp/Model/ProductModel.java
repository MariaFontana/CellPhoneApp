package com.example.acer.mynewponeapp.Model;

public class ProductModel {
    public String name;
    public double precio;
    public String photoId;
    public String description;
    public int cantidad;
    public Integer idBrand;
    public Integer idProduct;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }



    public ProductModel()
    {

    }

    public ProductModel(Integer idProduct, String name, double precio, String description,int cantidad,String photoId,Integer idBrand ) {
        this.name = name;
        this.precio = precio;
        this.description = description;
        this.photoId=photoId;
        this.cantidad=cantidad;
        this.idBrand=idBrand;
        this.idProduct=idProduct;
    }




    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }
    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
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
    public Integer getIdBrand() {
        return idBrand;
    }
    public Integer getIdProduct() {
        return this.idProduct;
    }
    public Double getPrecio() {
        return precio;
    }

    public String getDescription() {
        return description;
    }

   @Override
    public String toString()
   {
      return name;
   }


}

