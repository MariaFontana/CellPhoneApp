package com.example.acer.mynewponeapp.Model;

import java.math.BigInteger;

public class BrandModel  {

    public int idBrand;
    public String name;


    public BrandModel(int idBrand,String name)
    {
        this.idBrand=idBrand;
        this.name=name;
    }

    public BrandModel()
    {

    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getIdBrand() {
        return this.idBrand;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
