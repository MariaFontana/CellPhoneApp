package com.example.acer.mynewponeapp.Bussines;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, Channel.CHANNNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Hola, el alimento de tu mascota esta por terminarse, mandanos tu pedido.Gracias!!!!!")
                .setContentText("Ingresa tu nombre")
                .setAutoCancel(true);
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


