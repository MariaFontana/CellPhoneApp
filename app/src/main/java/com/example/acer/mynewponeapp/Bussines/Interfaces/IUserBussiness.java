package com.example.acer.mynewponeapp.Bussines.Interfaces;

import androidx.appcompat.widget.AppCompatSpinner;

import com.example.acer.mynewponeapp.Model.UserModel;

public interface IUserBussiness {

    void getBrand( AppCompatSpinner spinnerBrand);
    void  getBreed(AppCompatSpinner breedSpinner);
    void insertUserDataBase(UserModel user);
    void GetProductByBrand(Integer idBrand, AppCompatSpinner productSpinner);
}
