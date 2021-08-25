package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;

import androidx.appcompat.widget.AppCompatSpinner;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IUserBussiness;
import com.example.acer.mynewponeapp.DataBase.BrandAsync;
import com.example.acer.mynewponeapp.DataBase.BreedAsync;
import com.example.acer.mynewponeapp.DataBase.ProductByIdBrandAsync;
import com.example.acer.mynewponeapp.DataBase.backGround;
import com.example.acer.mynewponeapp.Factory.UserFactory;
import com.example.acer.mynewponeapp.Model.UserModel;

public class UserBussiness implements IUserBussiness {

    private Context context;
    UserFactory factory;

    public UserBussiness(Context context, UserFactory factory)
    {
        this.context=context;
        this.factory=factory;
    }

    @Override
    public void getBrand( AppCompatSpinner spinnerBrand) {
        try {

            BrandAsync brand = factory.getBrand(spinnerBrand);
            brand.execute();
        }
        catch (Exception e) {
            System.out.println("Problemas de conexion!");
        }

    }

    @Override
    public void getBreed( AppCompatSpinner breedSpinner) {
        try {

            BreedAsync breed =  factory.getBreed(breedSpinner);
            breed.execute();
        }
        catch (Exception e) {
            System.out.println("Problemas de conexion!");
        }

    }

    public void GetProductByBrand(Integer idBrand, AppCompatSpinner productSpinner)
    {
        try {

            ProductByIdBrandAsync productByBrand =  factory.GetProductByBrand(productSpinner);
            productByBrand.execute(idBrand.toString());
        }
        catch (Exception e) {
            System.out.println("Problemas de conexion!");
        }
    }

    @Override
    public void insertUserDataBase(UserModel user) {

        try {

            backGround bc = new backGround(context,user);
            bc.execute();
        }
        catch (Exception e) {
            System.out.println("Problemas de conexion!");
        }

    }
}
