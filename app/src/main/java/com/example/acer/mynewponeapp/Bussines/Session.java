package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.acer.mynewponeapp.Activity.ListProductActivity;
import com.example.acer.mynewponeapp.Activity.LoginActivity;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONStringer;

public class Session {

    private SharedPreferences prefs;
    private Context context;
    private String mailUser;
    private String passwordUser;
    private String nonbreUser;
    private String product;
    private String image;
    double precio;
    private Gson gson;
    private UserModel userModel;
    private ProductModel productModel;
    private UpdateNotificationModel updateNotificationModel;

    public Session(Context context) {
        // TODO Auto-generated constructor stub
        prefs = context.getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        this.context = context;
    }

    public void saveUpdateNotidicationModel(UpdateNotificationModel updateNotificationModel)
    {
        gson= new Gson();
        String updateNotificationModelString  =   gson.toJson(updateNotificationModel);
        prefs.edit().putString("updateNotificationModel", updateNotificationModelString).apply();
        //Save that String in SharedPreferences
    }
    public void saveUserModel(UserModel userModel)
    {
        gson= new Gson();
        String userModelString  =   gson.toJson(userModel);
        prefs.edit().putString("userModel", userModelString).apply();
        //Save that String in SharedPreferences
    }

    public void saveProductModel(ProductModel productModel)
    {
        gson= new Gson();
        String productModelString  =   gson.toJson(productModel);
        prefs.edit().putString("productModel", productModelString).apply();
        //Save that String in SharedPreferences

    }

    public UserModel GetUserModel()
    {
         gson = new Gson();
        String json = prefs.getString("userModel", "");
        return userModel  = gson.fromJson(json, UserModel.class);
    }

    public ProductModel GetProductModel()
    {
        gson = new Gson();
        String json = prefs.getString("productModel", "");
        return productModel  = gson.fromJson(json, ProductModel.class);
    }

    public UpdateNotificationModel GetNotificationModel()
    {
        gson = new Gson();
        String json = prefs.getString("updateNotificationModel", "");
        return updateNotificationModel  = gson.fromJson(json, UpdateNotificationModel.class);
    }
    public void setUserName(String userName) {
        prefs.edit().putString("usenameSession", userName).apply();

    }
    public void setPassword(String password) {
        prefs.edit().putString("passwordSession", password).apply();
    }

    public void setNombreUsuario(String nombreUser) {
        prefs.edit().putString("nombreUser", nombreUser).apply();
    }
    public void setuserProduct(String nombreUser) {
        prefs.edit().putString("userProduct", product).apply();
    }

    public void setuserProductPrecio(long userProductPrecio) {
        prefs.edit().putLong("userProductPrecio", userProductPrecio).apply();
    }

    public void setUserImage(String userImage) {
        prefs.edit().putString("userImage", userImage).apply();
    }

    public String getUserImage() {
        String userImage = prefs.getString("userImage","");
        return userImage;
    }

    public String getUserName() {
        String userNameSession = prefs.getString("userNameSession","");
        return userNameSession;
    }

    public String getUserPassword() {
        String passwordSession = prefs.getString("passwordSession","");
        return passwordSession;
    }

    public String getNameUser() {
        String nombreUser = prefs.getString("nombreUser","");
        return nombreUser;
    }

    public String getuserProduct() {
        String nombreUser = prefs.getString("userProduct","");
        return nombreUser;
    }
    public String getuserProductPrecio() {
        String nombreUser = prefs.getString("userProductPrecio","");
        return nombreUser;
    }

    public void SaveSharedPreferencesLogin(UserModel userModel,ProductModel productModel)
    {
        saveProductModel(productModel);
        saveUserModel(userModel);

    }

    public boolean ValidateUserSession()
    {
        boolean isLoged=false;

        UserModel userModel = GetUserModel();

        if(userModel != null) {

            String user = userModel.getName();

            if (user.isEmpty()) {
                //SaveSharedPreferencesLogin();
                isLoged = false;
            } else {
                isLoged = true;
            }
        }
        return isLoged;
    }

    public void LogOut()
    {
        prefs.edit().putString("productModel", null).apply();
        prefs.edit().putString("userModel", null).apply();
        prefs.edit().commit();
        Intent LoginActivity = new Intent(this.context, com.example.acer.mynewponeapp.Activity.LoginActivity.class);
        context.startActivity(LoginActivity);
    }

}
