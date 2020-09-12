package com.example.acer.mynewponeapp.Activity;

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

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Bussines.Validation;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;
import com.example.acer.mynewponeapp.DataBase.backGround;
import com.example.acer.mynewponeapp.R;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private EditText mail;
    private EditText password;
    private Button loginButton;
    private String mailUser = "";
    private String passwordUser = "";
    private Session session;

    TextView resul;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new Session(this);
        if (session.ValidateUserSession()) {

            this.startActivity(new Intent(this, ListProductActivity.class));
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

        if (!Validation.isEmailValid(mailUser)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

            GetUserByLogin loginUser = new GetUserByLogin(this,mailUser, passwordUser);
            loginUser.execute(mailUser, passwordUser);




    }

}