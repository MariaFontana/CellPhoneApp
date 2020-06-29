package com.example.acer.mynewponeapp.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Validation;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;
import com.example.acer.mynewponeapp.DataBase.backGround;
import com.example.acer.mynewponeapp.R;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private EditText mail;
    private EditText password;
    private Button loginButton;
    private String mailUser ="";
    private String passwordUser="";



    TextView resul;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = (EditText)findViewById(R.id.mailLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        loginButton=(Button)findViewById(R.id.buttonLogin);

    }

    public void login(View view) {

        mailUser = mail.getText().toString();
        passwordUser = password.getText().toString();

        if (!Validation.isEmailValid(mailUser)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }


        GetUserByLogin loginUser = new GetUserByLogin(this);
        loginUser.execute(mailUser,passwordUser);
        SaveSharedPreferencesLogin(mailUser,passwordUser);

        }


        private void SaveSharedPreferencesLogin(String mailUser,String passwordUser)
        {
            SharedPreferences sharedPref =getSharedPreferences("loginPreferences",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("mailUser", mailUser);
            editor.putString("passwordUser", passwordUser);
            editor.apply();
        }




    }

