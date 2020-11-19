package com.example.acer.mynewponeapp.Bussines.Interfaces;

import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;

public interface ISession {

    void saveUpdateNotidicationModel(UpdateNotificationModel updateNotificationModel);

    void saveUserModel(UserModel userModel);

    void saveProductModel(ProductModel productModel);

    UserModel GetUserModel();

    ProductModel GetProductModel();

    UpdateNotificationModel GetNotificationModel();

    void setUserName(String userName);
    void setPassword(String password);
    void setNombreUsuario(String nombreUser);
    public void setUserProduct(String nombreUser);
    public void setUserProductPrecio(long userProductPrecio);
    void setUserImage(String userImage);
    String getUserImage();
    String getUserName();
    String getUserPassword();
    String getNameUser();
    String getuserProduct();
    String getuserProductPrecio();
    void SaveSharedPreferencesLogin(UserModel userModel,ProductModel productModel);
    boolean ValidateUserSession();
    void LogOut();
}
