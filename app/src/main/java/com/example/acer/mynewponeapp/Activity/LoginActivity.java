package com.example.acer.mynewponeapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Bussines.Validation;
import com.example.acer.mynewponeapp.DataBase.UserByLogin;
import com.example.acer.mynewponeapp.R;

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
        mailUser=mailUser.trim();

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


    }


    private void LoginUserDatabase()
    {
        try {

            UserByLogin loginUser = new UserByLogin(this,mailUser, passwordUser);
            loginUser.execute(mailUser, passwordUser);
        }

      catch (Exception e) {
            Toast.makeText(this, "El usuario o la password no son correctos", Toast.LENGTH_LONG).show();
        }

    }

}