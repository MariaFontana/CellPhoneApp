package com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Converter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Entity(tableName = "user")
public class user implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
        private int idUser;

    @ColumnInfo(name = "name")
        private String name;
    @ColumnInfo(name = "address")
        private String address;
    @ColumnInfo(name = "telephone")
        private String telephone;
    @ColumnInfo(name = "pet")
        private String pet;
    @ColumnInfo(name = "mail")
    private String mail;
    @ColumnInfo(name = "dateCount")
        private int dateCount;
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "dateStart")
    @TypeConverters(Converter.class)
    private Date dateStart;

    public user(  String name, String address, String telephone, String pet, String mail, int dateCount, String password, Date  dateStart) {

        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.pet = pet;
        this.mail = mail;
        this.dateCount = dateCount;
        this.password = password;
        this.dateStart = dateStart;
    }

    @Ignore
    public user() {

    }


    protected user(Parcel in) {
        idUser = in.readInt();
        name = in.readString();
        address = in.readString();
        telephone = in.readString();
        pet = in.readString();
        mail = in.readString();
        dateCount = in.readInt();
        password = in.readString();
    }

    public static final Creator<user> CREATOR = new Creator<user>() {
        @Override
        public user createFromParcel(Parcel in) {
            return new user(in);
        }

        @Override
        public user[] newArray(int size) {
            return new user[size];
        }
    };

    @NonNull
    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPet() {
        return pet;
    }

    public String getMail() {
        return mail;
    }

    public int getDateCount() {
        return dateCount;
    }

    public String getPassword() {
        return password;
    }

    public Date  getDateStart() {
        return dateStart;
    }

    public void setIdUser(@NonNull int idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDateCount(int dateCount) {
        this.dateCount = dateCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateStart(Date  dateStart) {
        this.dateStart = dateStart;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idUser);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(telephone);
        dest.writeString(pet);
        dest.writeString(mail);
        dest.writeInt(dateCount);
        dest.writeString(password);
    }
}
