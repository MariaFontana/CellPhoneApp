package com.example.acer.mynewponeapp.Bussines;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Interfaces.ICreateUserBusiness;
import com.example.acer.mynewponeapp.DataBase.GetBrand;
import com.example.acer.mynewponeapp.DataBase.GetBreedAsync;
import com.example.acer.mynewponeapp.DataBase.backGround;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel.BrandViewModel;

import androidx.appcompat.widget.AppCompatSpinner;

import static java.lang.Integer.parseInt;

public class CreateUserBusiness implements ICreateUserBusiness {

    private Context context;
    public CreateUserBusiness(Context context)
    {
        this.context=context;
    }

    @Override
    public void getBrand(  AppCompatSpinner spinnerBrand, BrandViewModel brandViewModel) {
        try {

            GetBrand brand = new GetBrand(context,spinnerBrand,brandViewModel);
            brand.execute();
        }
        catch (Exception e) {

        }

    }

    @Override
    public void getBreed(AppCompatSpinner breedSpinner) {
        try {

            GetBreedAsync breed = new GetBreedAsync(context,breedSpinner);
            breed.execute();
        }
        catch (Exception e) {

        }
    }

    @Override
    public void insertUserDataBase(UserModel user) {

        try {

            backGround bc = new backGround(context,user);
            bc.execute();
        }
        catch (Exception e) {

        }

    }
}
