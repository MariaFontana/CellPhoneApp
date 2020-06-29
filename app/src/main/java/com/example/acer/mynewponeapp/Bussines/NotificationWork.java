package com.example.acer.mynewponeapp.Bussines;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.acer.mynewponeapp.DataBase.CheckDayOfNotification;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.notificationView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import static android.content.Context.NOTIFICATION_SERVICE;



public class NotificationWork extends Worker {

    // Define the parameter keys:
    public static  String mail = "";
    public static  String contraseña = "";

    // ...and the result key:
    public static final String KEY_RESULT = "result";



    public NotificationWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        mail = getInputData().getString(mail);
        contraseña = getInputData().getString(contraseña);

        ExecuteValidationNotification();

      return  Result.success();

    }
        public void CreateNotification()
        {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), Channel.CHANNNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_email)
                    .setContentTitle("Hola, el alimento de tu mascota esta por terminarse, mandanos tu pedido.Gracias!!!!!")
                    .setContentText("Ingresa tu nombre")
                    .setAutoCancel(true);
            NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(Channel.NOTIFICATION_ID, mBuilder.build());

            //Pending intent for a notification button named More
           // PendingIntent morePendingIntent = PendingIntent.getBroadcast(
              //      notificationView.this,
                //    Channel.REQUEST_CODE_MORE,
                //    new Intent(notificationView.this, NotificationReceiver.class)
                        //    .putExtra(Channel.KEY_INTENT_MORE, Channel.REQUEST_CODE_MORE),
                   // PendingIntent.FLAG_UPDATE_CURRENT
           // );


            //Pending intent for a notification button help
           // PendingIntent helpPendingIntent = PendingIntent.getBroadcast(
                 //   notificationView.this,
                  //  Channel.REQUEST_CODE_HELP,
                   // new Intent(notificationView.this, NotificationReceiver.class)
                           // .putExtra(Channel.KEY_INTENT_HELP, Channel.REQUEST_CODE_HELP),
                   // PendingIntent.FLAG_UPDATE_CURRENT
           // );


            //We need this object for getting direct input from notification
         //   RemoteInput remoteInput = new RemoteInput.Builder(Channel.NOTIFICATION_REPLY)
               //     .setLabel("Please enter your name")
                 //   .build();


            //For the remote input we need this action object
          //  NotificationCompat.Action action =
                  //  new NotificationCompat.Action.Builder(android.R.drawable.ic_delete,
                   //         "Reply Now...", helpPendingIntent)
                       //     .addRemoteInput( remoteInput)
                       //     .build();

            //Creating the notifiction builder object

                   // .setContentIntent(helpPendingIntent)
                 //   .addAction(action)
                 //   .addAction(android.R.drawable.ic_menu_compass, "More", morePendingIntent)
                 //   .addAction(android.R.drawable.ic_menu_directions, "Help", helpPendingIntent);


            //finally displaying the notification

        }

        private String ExecuteValidationNotification()
        {
            try{

                String link = "http://192.168.0.109:8080/CheckNotification.php";


                String data  = URLEncoder.encode("mail", "UTF-8") + "=" +
                        URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(contraseña, "UTF-8");


                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();
            } catch(Exception e){
                return new String("Exception: " + e.getMessage());

            }


        }

}





