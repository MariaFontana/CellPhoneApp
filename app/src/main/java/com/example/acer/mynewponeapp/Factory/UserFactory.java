package com.example.acer.mynewponeapp.Factory;


import android.content.Context;

import androidx.appcompat.widget.AppCompatSpinner;

import com.example.acer.mynewponeapp.DataBase.BrandAsync;
import com.example.acer.mynewponeapp.DataBase.BreedAsync;
import com.example.acer.mynewponeapp.DataBase.ProductByIdBrandAsync;

public  class UserFactory {

    Context contextService;
    AppCompatSpinner spinner;


    public UserFactory(Context context) {
        this.contextService = context;

    }

    public BrandAsync getBrand(AppCompatSpinner brandSpinner) {

        return new BrandAsync(contextService, brandSpinner);
    }

    public BreedAsync getBreed(AppCompatSpinner breedSpinner) {

        return new BreedAsync(contextService, breedSpinner);
    }

    public ProductByIdBrandAsync GetProductByBrand( AppCompatSpinner productSpinner) {
            return new ProductByIdBrandAsync(contextService, productSpinner);

    }

}



