package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.acer.mynewponeapp.Activity.ListProductActivity;

public class Session {

    private SharedPreferences prefs;
    private Context context;
    private String mailUser;
    private String passwordUser;
    private String nonbreUser;
    private String product;
    private String image;
    double precio;

    public Session(Context context,String mailUser,String passwordUser,String nombreUser,String product, double precio, String image) {
        // TODO Auto-generated constructor stub
        prefs =context.getSharedPreferences("loginPreferences",Context.MODE_PRIVATE);
        this.context=context;
        this.mailUser=mailUser;
        this.passwordUser=passwordUser;
        this.nonbreUser=nombreUser;
        this.product=product;
        this.precio=precio;
        this.image=image;
    }

    public Session(Context context,String mailUser,String passwordUser,String nombreUser) {
        // TODO Auto-generated constructor stub
        prefs =context.getSharedPreferences("loginPreferences",Context.MODE_PRIVATE);
        this.context=context;
        this.mailUser=mailUser;
        this.passwordUser=passwordUser;
        this.nonbreUser=nombreUser;
        this.image=image;

    }
    public Session(Context context) {
        // TODO Auto-generated constructor stub

       // prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs =context.getSharedPreferences("loginPreferences",Context.MODE_PRIVATE);
        this.context=context;

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

    public void SaveSharedPreferencesLogin()
    {
        long precioProduct = (new Double(precio)).longValue();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userNameSession", mailUser);
        editor.putString("passwordSession", passwordUser);
        editor.putString("nombreUser",nonbreUser);
        editor.putString("userProduct",product);
        editor.putLong("userProductPrecio",precioProduct);
        editor.putString("userImage",image);
        editor.commit();
    }

    public boolean ValidateUserSession()
    {
        boolean isLoged=false;
        String user= getUserName();
        if(user.isEmpty())
        {
            SaveSharedPreferencesLogin();
            isLoged=false;
        }
        else
        {
            isLoged=true;
        }
        return isLoged;
    }

}
