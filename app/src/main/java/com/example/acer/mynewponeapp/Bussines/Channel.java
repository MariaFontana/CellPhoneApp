package com.example.acer.mynewponeapp.Bussines;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public  final class Channel {

    String CHANNEL_ID = "my_channel_01";
    public static final String NOTIFICATION_REPLY = "NotificationReply";
    public static final String CHANNNEL_ID = "SimplifiedCodingChannel";
    public static final String CHANNEL_NAME = "SimplifiedCodingChannel";
    public static final String CHANNEL_DESC = "This is a channel for Simplified Coding Notifications";

    public static final String KEY_INTENT_MORE = "keyintentmore";
    public static final String KEY_INTENT_HELP = "keyintenthelp";

    public static final int REQUEST_CODE_MORE = 100;
    public static final int REQUEST_CODE_HELP = 101;
    public static final int NOTIFICATION_ID = 200;


    public final static void createNotificationChannel(Context mContext) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Animalia Compra";
            String description = "Compra de Alimento";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Channel.CHANNNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =  mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}
