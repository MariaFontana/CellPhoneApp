package com.example.acer.mynewponeapp.Bussines;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.acer.mynewponeapp.Activity.MainActivity;
import com.example.acer.mynewponeapp.Bussines.Interfaces.INotificationBusiness;
import com.example.acer.mynewponeapp.DataBase.GetNotificationByUserAsync;
import com.example.acer.mynewponeapp.DataBase.GetUpdateNotificationAsync;
import com.example.acer.mynewponeapp.DataBase.UpdateNotificationAsync;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;

import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

public class NotificaionBussines implements INotificationBusiness {

    private Date dateNotification;
    private UpdateNotificationAsync updateNotificationAsync;
    Context context;
    UserModel userModel;
    Session session;
    UpdateNotificationModel updateNotificationModel;
    Date newDayUpdate;
    Calendar dayOfNotification;
    int days;

    public NotificaionBussines(Context context)
    {

        this.context=context;
        session = new Session(context);
       // GetSessionUser();
    }
    public NotificaionBussines(Context context, UpdateNotificationModel updateNotificationModel)
    {
        session = new Session(context);
        this.context=context;
       this.updateNotificationModel=updateNotificationModel;

    }
    //private void GetSessionUser()
    //{
      //  userModel= session.GetUserModel();
    //}

    public void CalculateAlarmNotification()
    {
        try {
            Intent intent = new Intent(context, NotificationReceiver.class);
            PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);

            AlarmManager alarm = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            //set timer you want alarm to work (here I have set it to 7.20pm)
            // updateNotificationModel= session.GetNotificationModel();

            if (updateNotificationModel == null && userModel == null) {
                //Get Today
                dayOfNotification = Calendar.getInstance();
                dayOfNotification.add(Calendar.DATE, days);
                newDayUpdate = dayOfNotification.getTime();


            } else if (updateNotificationModel != null) {
                dayOfNotification = Calendar.getInstance();
                Date date = updateNotificationModel.getDateUpdate();
                dayOfNotification.setTime(date);
                dayOfNotification.add(Calendar.DAY_OF_YEAR, updateNotificationModel.getCountDays());
                dayOfNotification.set(Calendar.HOUR_OF_DAY, 10);
                long time2=dayOfNotification.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP,time2,pending);

                //UpdateNotificationModel updateNotificationModelNew= new UpdateNotificationModel(null,newDayUpdate,userModel.getDiasCount(),userModel);
                // updateNotificationAsync= new UpdateNotificationAsync(context,updateNotificationModel);
                // updateNotificationAsync.execute();
            }

            // numero de días a añadir, o restar en caso de días<0
            //Calendar dayOfNotification = Calendar.getInstance();
            //Calendar dayOfNotification = Calendar.getInstance();
           // long time = System.currentTimeMillis();
            //long timeseconf = 1000 * 10;
            //dayOfNotification.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
            //long time2=dayOfNotification.getTimeInMillis();
            //Date after adding the days to the given date

            //Displaying the new Date after addition of Days

            // alarm.set(AlarmManager.RTC_WAKEUP,time2,pending);
           // alarm.set(AlarmManager.RTC_WAKEUP, 60000, pending);
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }




    @Override
    public void getLastNotification() {

        GetUpdateNotificationAsync updateAsync= new GetUpdateNotificationAsync(context,false);
        updateAsync.execute();
    }


    @Override
    public void updateNotification() {

        UpdateNotificationAsync updateNotificationAsync= new UpdateNotificationAsync(context,updateNotificationModel);
        updateNotificationAsync.execute();
    }

    //Lenar la lista de notificaciones
    @Override
    public void getListNotificationByUser() {
        GetNotificationByUserAsync getNotificationByUserAsync= new GetNotificationByUserAsync(context);
        getNotificationByUserAsync.execute();
    }
}
