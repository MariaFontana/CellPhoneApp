package com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "brand")
public class Brand  {

    @NonNull
    @PrimaryKey
    public int idBrand;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    public Brand(int idBrand,String name,String description)
    {
        this.idBrand=idBrand;
        this.name=name;
    }
    @Ignore
    public Brand()
    {

    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
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

