package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IActivityHomeBussines;
import com.example.acer.mynewponeapp.DataBase.GetNotificationByUserAsync;
import com.example.acer.mynewponeapp.Model.UserModel;

public class ActivityHomeBussine implements IActivityHomeBussines {

    UserModel userModel;
    Context context;
    NotificaionBussines notificationBusiness;

    public ActivityHomeBussine(Context context, UserModel userModel)
    {
        this.context=context;
        this.userModel=userModel;
        this.notificationBusiness= new NotificaionBussines(context);
    }

    //Obtiene todas las notificaciones del usuario
    @Override
    public void GetLastNotification(int IdUser) {
       notificationBusiness.getListNotificationByUser();
    }
}
