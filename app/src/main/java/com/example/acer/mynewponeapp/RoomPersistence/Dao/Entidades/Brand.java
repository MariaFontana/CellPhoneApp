package com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "brand")
public class Brand implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int idBrand;
    @ColumnInfo(name = "name")
    public String name;


    public Brand(int idBrand,String name)
    {
        this.idBrand=idBrand;
        this.name=name;
    }

    @Ignore
    public Brand()
    {

    }

    protected Brand(Parcel in) {
        idBrand = in.readInt();
        name = in.readString();
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idBrand);
        dest.writeString(name);
    }
}

