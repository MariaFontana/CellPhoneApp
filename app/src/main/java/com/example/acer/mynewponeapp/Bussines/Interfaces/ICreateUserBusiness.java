package com.example.acer.mynewponeapp.Bussines.Interfaces;

import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel.BrandViewModel;

import androidx.appcompat.widget.AppCompatSpinner;

public interface ICreateUserBusiness {

    void getBrand( AppCompatSpinner spinnerBrand, BrandViewModel brandViewModel);

    void  getBreed(AppCompatSpinner breedSpinner);

    void insertUserDataBase(UserModel user);
}
