package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;
import android.os.AsyncTask;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IPerfilFragmentBusiness;
import com.example.acer.mynewponeapp.DataBase.GetNotificationByUserAsync;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;

public class PerfilFragmentBusiness implements IPerfilFragmentBusiness {

    Context context;
    Session session;
    public PerfilFragmentBusiness(Context context,Session session)
    {
        this.context=context;
        this.session=session;
    }

    @Override
    public void GetUserModel() {

        boolean status=false;
        String mail=session.GetUserModel().getMail();
        String password=session.GetUserModel().getPassword();

        GetUserByLogin getUser = new GetUserByLogin(context, mail, password);
        getUser.execute();

    }

    @Override
    public void GetUpdateNotification() {
      //  UpdateNotificaionBussines updateNotificaionBussines = new UpdateNotificaionBussines(context);
        //updateNotificaionBussines.CalculateAlarmNotification();
    }

    @Override
    public void GetProductBussiness() {

    }
}
