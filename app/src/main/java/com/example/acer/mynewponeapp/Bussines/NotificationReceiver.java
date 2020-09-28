package com.example.acer.mynewponeapp.Bussines;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;


import com.example.acer.mynewponeapp.R;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String message = String.valueOf(R.string.messageWhatsApp) ;// Replace with your message.

        String toNumber =  "542615568504" ;

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
       // Uri uri = Uri.parse("smsto:" + "");
        sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+ toNumber +"&text="+ message));

        //Intent shareIntent = new Intent(Intent.ACTION_SEND);


        PendingIntent pendingShareIntent = PendingIntent.getActivity(context, 0, Intent.createChooser(sendIntent, "Comunicate con nosotros!!!"),
                PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, Channel.CHANNNEL_ID)
                .setSmallIcon(android.R.drawable.btn_star_big_on)
                .setContentTitle("Hola, el alimento de tu mascota esta por terminarse, Hacenos tu pedido yaa!!!!!")
                .setAutoCancel(true)
                .addAction(android.R.drawable.btn_star, "Open WhatssApp...", pendingShareIntent);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(Channel.NOTIFICATION_ID, mBuilder.build());


        //getting the remote input bundle from intent
      //  Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        //if there is some input
       // if (remoteInput != null) {

            //getting the input value
          //  CharSequence name = remoteInput.getCharSequence(Channel.NOTIFICATION_REPLY);

            //updating the notification with the input value
         //   NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, Channel.CHANNNEL_ID)
             //       .setSmallIcon(android.R.drawable.ic_menu_info_details)
             //       .setContentTitle("Hey Thanks, " + name);
            //NotificationManager notificationManager = (NotificationManager) context.
               //     getSystemService(Context.NOTIFICATION_SERVICE);
           // notificationManager.notify(Channel.NOTIFICATION_ID, mBuilder.build());
        }




        //if help button is clicked
       // if (intent.getIntExtra(Channel.KEY_INTENT_HELP, -1) == Channel.REQUEST_CODE_HELP) {
          //  Toast.makeText(context, "You Clicked Help", Toast.LENGTH_LONG).show();
       // }

        //if more button is clicked
       // if (intent.getIntExtra(Channel.KEY_INTENT_MORE, -1) == Channel.REQUEST_CODE_MORE) {
           // Toast.makeText(context, "You Clicked More", Toast.LENGTH_LONG).show();
       // }
    }


