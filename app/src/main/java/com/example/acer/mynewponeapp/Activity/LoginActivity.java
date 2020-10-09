package com.example.acer.mynewponeapp.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.NotificationReceiver;
import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Bussines.UpdateNotificaionBussines;
import com.example.acer.mynewponeapp.Bussines.Validation;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;
import com.example.acer.mynewponeapp.DataBase.backGround;
import com.example.acer.mynewponeapp.R;


import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private EditText mail;
    private EditText password;
    private Button loginButton;
    private String mailUser = "";
    private String passwordUser = "";
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new Session(this);
        if (session.ValidateUserSession()) {

            this.startActivity(new Intent(this, ActivityHome.class));
        } else {

            setContentView(R.layout.activity_login);
            mail = (EditText) findViewById(R.id.mailLogin);
            password = (EditText) findViewById(R.id.passwordLogin);
            loginButton = (Button) findViewById(R.id.buttonLogin);
        }

    }

    public void login(View view) {

        mailUser = mail.getText().toString();
        passwordUser = password.getText().toString();

       if(!Validation.passwordValidateStringEmpty(passwordUser))
       {
           Toast.makeText(this, "La contraseña no puede estar vacio", Toast.LENGTH_SHORT).show();
           return;
       }

        if(!Validation.UserNameValidateStringEmpty(mailUser))
        {
            Toast.makeText(this, "el usuaario no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Validation.isEmailValid(mailUser)) {
            Toast.makeText(this, "El formato del mail es inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginUserDatabase();

        UpdateNotificaionBussines updateNotificaionBussines = new UpdateNotificaionBussines(this);
        updateNotificaionBussines.CalculateAlarmNotification();

       // Intent intent = new Intent(LoginActivity.this, NotificationReceiver.class);
        //intent.putExtra("openWhatsApp", "1");
        //PendingIntent pending=  PendingIntent.getBroadcast(LoginActivity.this,0,intent,0);


        //AlarmManager alarm= (AlarmManager) getSystemService(ALARM_SERVICE);
        //set timer you want alarm to work (here I have set it to 7.20pm)
        //Calendar dayOfNotification = Calendar.getInstance();
        //long time= System.currentTimeMillis();
       // long timeseconf=1000*10;
        //dayOfNotification.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
        //long time2=dayOfNotification.getTimeInMillis();
        //Date after adding the days to the given date

        //Displaying the new Date after addition of Days


        // alarm.set(AlarmManager.RTC_WAKEUP,time2,pending);
        //alarm.set(AlarmManager.RTC_WAKEUP, 60000, pending);

    }


    private void LoginUserDatabase()
    {
        try {

            GetUserByLogin loginUser = new GetUserByLogin(this,mailUser, passwordUser);
            loginUser.execute(mailUser, passwordUser);
        }

      catch (Exception e) {
            Toast.makeText(this, "El usuario o la password no son correctos", Toast.LENGTH_LONG).show();
        }

    }

}