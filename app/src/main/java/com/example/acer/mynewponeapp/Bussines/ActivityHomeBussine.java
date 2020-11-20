package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IActivityHomeBussines;
import com.example.acer.mynewponeapp.DataBase.GetNotificationByUserAsync;
import com.example.acer.mynewponeapp.Model.UserModel;

public class ActivityHomeBussine implements IActivityHomeBussines {
    UserModel userModel;
    Context context;

    public ActivityHomeBussine(Context context, UserModel userModel)
    {
        this.context=context;
        this.userModel=userModel;
    }
    @Override
    public void GetLastNotification(int IdUser) {

        GetNotificationByUserAsync getNotificationByUserAsync= new GetNotificationByUserAsync(context);
        getNotificationByUserAsync.execute();
    }
}
