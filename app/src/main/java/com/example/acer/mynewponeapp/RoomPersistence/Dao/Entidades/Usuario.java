package com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades;

import java.io.Serializable;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "usuario")
public class Usuario implements Serializable {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
        private int id;
    @ColumnInfo(name = "nombre")
        private String mNombre;
    @ColumnInfo(name = "direccion")
        private String mDireccion;
    @ColumnInfo(name = "telefono")
        private String mTelefono;
    @ColumnInfo(name = "mascota")
        private String mMascota;
    @ColumnInfo(name = "alimento")
        private String mAlimento;
    @ColumnInfo(name = "mail")
    private String mMail;
    @ColumnInfo(name = "dia")
        private String mDia;
    @ColumnInfo(name = "password")
    private String mpassword;



    @Ignore
        public Usuario(@NonNull String nombre,@NonNull String telefono,@NonNull String direccion,@NonNull String mascota, @NonNull String alimento,  @NonNull String mail,@NonNull String password) {
            this.mNombre = nombre;
            this.mAlimento=alimento;
            this.mDireccion=direccion;
            this.mMascota=mascota;
            this.mTelefono=telefono;
            this.mMail=mail;
            this.mpassword=password;

        }

        public Usuario()
        {}

    @NonNull
        public String getNombre() {
            return this.mNombre;
        }
    @NonNull
        public String getDireccion() { return this.mDireccion;}
    @NonNull
        public String getMascota() { return this.mMascota;}
    @NonNull
        public String getAlimento() {return this.mAlimento;}
    @NonNull
    public String getMail(){return this.mMail;}
    @NonNull
        public String getDia(){return this.mDia;}
    @NonNull
        public String getTelefono() {return this.mTelefono;}
    @NonNull
        public int getId(){return this.id;}

    @NonNull
    public String getMpassword(){return this.mpassword;}


    public void setNombre(String nombre) {
        this.mNombre = nombre;
    }

    public void setMascota(String mascota) {
        this.mMascota = mascota;
    }
    public void setDireccion(String direccion) {
        this.mDireccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.mTelefono = telefono;
    }

    public void setAlimento(String alimento) {
        this.mAlimento = alimento;
    }
    public void setMail(String mail) { this.mMail = mail; }
    public void setDia(String dia) {
        this.mDia = dia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMpassword(String password) {
        this.mpassword = password;
    }



}
