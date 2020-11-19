package com.example.acer.mynewponeapp.Bussines;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.example.acer.mynewponeapp.Activity.LoginActivity;
import com.example.acer.mynewponeapp.Bussines.Interfaces.InotificationReceiver;
import com.example.acer.mynewponeapp.DataBase.GetUpdateNotificationAsync;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.R;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;



public class NotificationReceiver extends BroadcastReceiver implements InotificationReceiver  {

    Context context;
    Intent intent;
    boolean IsAlarmSet=false;

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

    }

    @Override
    public void UpdateNotificationAsync() {

        GetUpdateNotificationAsync updateAsync= new GetUpdateNotificationAsync(context,IsAlarmSet);
        updateAsync.execute();
    }
}


