package com.example.acer.mynewponeapp.Model;

import java.math.BigInteger;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "brand")
public class BrandModel  {

    @NonNull
    @PrimaryKey
    public int idBrand;

    @ColumnInfo(name = "name")
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
