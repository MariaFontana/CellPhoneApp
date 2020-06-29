package com.example.acer.mynewponeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Channel;
import com.example.acer.mynewponeapp.Bussines.NotificationReceiver;
import com.example.acer.mynewponeapp.Bussines.NotificationWork;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.UsuarioViewModel;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Usuario;
import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.DataBase.backGround;
import  com.example.acer.mynewponeapp.Bussines.Validation;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import static com.example.acer.mynewponeapp.Bussines.NotificationWork.contraseña;
import static com.example.acer.mynewponeapp.Bussines.NotificationWork.mail;


public class MainActivity extends AppCompatActivity {

    private UsuarioViewModel mUsuarioViewModel;

EditText nametext,phonetext,direccionTxt,mascotaTxt,alimentoTxt,diaTxt,mailTxt,passwordtxt,repitPasswordtxt;

    private Usuario usuario;
    private UsuarioViewModel usuarioViewModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetValuesIU();

        setValuesToolbar();

        Channel.createNotificationChannel(this);

        usuarioViewModel = new UsuarioViewModel(getApplication());

    }

    private void SetValuesIU() {
        nametext=(EditText)findViewById(R.id.name);
        phonetext=(EditText)findViewById(R.id.phone);
        direccionTxt = (EditText)findViewById(R.id.direccion);
        mascotaTxt = (EditText)findViewById(R.id.mascota);
        alimentoTxt = (EditText)findViewById(R.id.alimento);
        diaTxt = (EditText)findViewById(R.id.dia);
        mailTxt = (EditText)findViewById(R.id.mail);
        passwordtxt= (EditText)findViewById(R.id.contraseña);
        repitPasswordtxt= (EditText)findViewById(R.id.RepetPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    private void setValuesToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("APPANIMALIAS");

        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#0D357C"));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ingresarMenuBar:
                goLogin();
                return true;
            case R.id.helpMenuBar:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goLogin()
    {
        Intent LoginActivity = new Intent(this, LoginActivity.class);
        startActivity(LoginActivity);
    }

    public void loginUser(View view) {
        try {

           String nombre = nametext.getText().toString();
           String phone = phonetext.getText().toString();
           String direccion = direccionTxt.getText().toString();
           String mascota = mascotaTxt.getText().toString();
           String alimento = alimentoTxt.getText().toString();
           String mail = mailTxt.getText().toString();
            String dia = diaTxt.getText().toString();
            String password = passwordtxt.getText().toString();
            String repetPassword= repitPasswordtxt.getText().toString();



            Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
            PendingIntent pending=  PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
            AlarmManager alarm= (AlarmManager) getSystemService(ALARM_SERVICE);





            //set timer you want alarm to work (here I have set it to 7.20pm)

            Calendar dayOfNotification = Calendar.getInstance();

            long time= System.currentTimeMillis();
            long timeseconf=1000*10;
             //dayOfNotification.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
             //long time2=dayOfNotification.getTimeInMillis();
            //Date after adding the days to the given date

            //Displaying the new Date after addition of Days


           // alarm.set(AlarmManager.RTC_WAKEUP,time2,pending);
            alarm.set(AlarmManager.RTC_WAKEUP, 60000, pending);


           if (!Validation.IsEmptyRegister(nombre, direccion, phone, alimento, dia, mail,contraseña)) {
                Toast.makeText(this,R.string.requeridField, Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Validation.ValidatePassword(contraseña,repetPassword))

            if (!Validation.isEmailValid(mail)) {
                Toast.makeText(this,R.string.passwordEqual, Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Validation.IsNumeric(dia)) {
                Toast.makeText(this, R.string.errorDia, Toast.LENGTH_SHORT).show();
                return;
            }

            createUserRoom(nombre, phone, direccion, mascota, alimento, mail,dia,contraseña);

            insertUserDataBase(nombre, phone, direccion, mascota, alimento, mail,dia,contraseña);


          // Usuario user = usuarioViewModel.GetUserByLogin("ma@gmail.com","123456");

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

        private void insertUserDataBase(String nombre,String telefono,String direccion,String mascota,String alimento,String mail ,String dia,String contraseña)
        {
            try {
                backGround bc = new backGround(this);
                bc.execute(nombre, telefono, direccion ,mascota , alimento,mail, dia,contraseña);
            }
            catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        private void createUserRoom(String nombre,String phone,String direccion,String mascota,String alimento,String mail,String dia,String contraseña)
        {
            try {
                    usuario = new Usuario(nombre, phone, direccion, mascota, alimento, mail,contraseña);
                    usuarioViewModel.insert(usuario);
                }
              catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }









    }











