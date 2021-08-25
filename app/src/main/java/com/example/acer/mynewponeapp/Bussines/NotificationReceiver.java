package com.example.acer.mynewponeapp.Bussines;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.example.acer.mynewponeapp.Bussines.Interfaces.InotificationReceiver;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.R;

import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;



public class NotificationReceiver extends BroadcastReceiver implements InotificationReceiver  {

    Context context;
    Intent intent;
    boolean IsAlarmSet=false;
    private Session sessionUser ;
    private UserModel userModel;
    @Override
    public void onReceive(Context context, Intent intent) {

        this.context=context;
        this.intent=intent;

        CreateNotificationBody();

        }


    @Override
    public void CreateNotificationBody() {

        String message = String.valueOf(R.string.messageWhatsApp) ;// Replace with your message.

        String toNumber =  "542615568504" ;
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        // Uri uri = Uri.parse("smsto:" + "");
        sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+ toNumber +"&text="+ message));

        //Intent shareIntent = new Intent(Intent.ACTION_SEND);


        PendingIntent pendingShareIntent = PendingIntent.getActivity(context, 0, Intent.createChooser(sendIntent, "Comunicate con nosotros!!!"),
                PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, Channel.CHANNNEL_ID)
                .setSmallIcon(android.R.drawable.btn_star)
                .setContentTitle("Hola, el alimento de tu mascota esta por terminarse, Hacenos tu pedido yaa!!!!!")
                .setAutoCancel(true)
                .addAction(android.R.drawable.btn_star, "Open WhatssApp...", pendingShareIntent);


        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(Channel.NOTIFICATION_ID, mBuilder.build());

        boolean IsAlarmSet=true;

        sessionUser = new Session(context);

        userModel= sessionUser.GetUserModel();

        if(userModel.getListNotificationModelList().size()> 0) {

            UpdateNotificationModel    updateNotificationModel = userModel.getListNotificationModelList().get(0);

            updateNotificationModel.setUserModel(userModel);
            updateNotificationModel.setDateUpdate(new Date());
            if (updateNotificationModel != null) {
                NotificaionBussines noti = new NotificaionBussines(context, updateNotificationModel);
                noti.updateNotification();
            }
        }

    }





}


