package com.example.acer.mynewponeapp.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.mynewponeapp.R;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private EditText user;
    private EditText password;
    private Button loginButton;


    TextView resul;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // user = (EditText) findViewById(R.id.name);
        //    //    password = (EditText) findViewById(R.id.password);
        //     //   loginButton=(Button)findViewById(R.id.buttonLogin);

    }

    public void login(View view) {


        if (user.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {

            Toast.makeText(getApplicationContext(),
                    "Redirecting...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
            user.setVisibility(View.VISIBLE);
            user.setBackgroundColor(Color.RED);
            counter--;
            resul.setText(Integer.toString(counter));

            if (counter == 0) {
                loginButton.setEnabled(false);
            }
        }


    }
}
