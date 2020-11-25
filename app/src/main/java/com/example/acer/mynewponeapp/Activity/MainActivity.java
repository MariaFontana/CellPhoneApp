package com.example.acer.mynewponeapp.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Channel;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.DataBase.GetBrand;
import com.example.acer.mynewponeapp.DataBase.GetBreedAsync;
import com.example.acer.mynewponeapp.DataBase.GetProductByIdBrand;
import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.Model.BreedModel;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.DataBase.backGround;
import  com.example.acer.mynewponeapp.Bussines.Validation;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel.BrandViewModel;


import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    EditText nametext, phonetext, direccionTxt, mascotaTxt, alimentoTxt, diaTxt, mailTxt, passwordtxt, repitPasswordtxt;
    private Button buttonRegistre;
    AppCompatSpinner spinnerBrand;
    AppCompatSpinner spinnerProduct;
    AppCompatSpinner breedSpinner;

    private Session sessionUser;
    private int position;
    private String selection;
    ProductModel productItem;
    BreedModel breedItem;
    BrandModel brandItem;
    private UserModel user;
    public boolean saveUser=false;
    private BrandViewModel brandViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        sessionUser = new Session(this);


        if (sessionUser.ValidateUserSession()) {

            this.startActivity(new Intent(this, ActivityHome.class));

            //this.startActivity(new Intent(this, ListProductActivity.class));
        } else {

            setContentView(R.layout.activity_main);

            SetValuesIU();

            //GetAllBrand();

            //  RetrievedBrand();

            GetBrand();
            GetBreed();

          //  GetBrandRoom();

            //     RetrievedBrand();

            setValuesToolbar();

            Channel.createNotificationChannel(this);
        }
        // usuarioViewModel = new UsuarioViewModel(getApplication());

    }


    private void SetValuesIU() {
        try {
            nametext=(EditText)findViewById(R.id.name);
            phonetext=(EditText)findViewById(R.id.phone);
            direccionTxt = (EditText)findViewById(R.id.direccion);
            mascotaTxt = (EditText)findViewById(R.id.mascota);
            diaTxt = (EditText)findViewById(R.id.dia);
            mailTxt = (EditText)findViewById(R.id.mail);
            passwordtxt= (EditText)findViewById(R.id.contraseña);
            repitPasswordtxt= (EditText)findViewById(R.id.RepetPassword);
            buttonRegistre=findViewById(R.id.buttonLogin);
            spinnerBrand = (AppCompatSpinner) findViewById(R.id.brandSpinner);
            breedSpinner = (AppCompatSpinner) findViewById(R.id.breedSpinner);
            spinnerProduct = (AppCompatSpinner) findViewById(R.id.productSpinner);
            spinnerBrand.setOnItemSelectedListener(this);
            spinnerProduct.setOnItemSelectedListener(this);
            breedSpinner.setOnItemSelectedListener(this);

        }

       catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
        //toolbar.setTitle("Registrate en Animalias");

        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.ColorPrimary));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ingresarMenuBar:
                goLogin();
                return true;
            case R.id.salirMenuBar:
                cerrarSession();
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

    private void cerrarSession()
    {
        sessionUser.LogOut();
        Intent LoginActivity = new Intent(this, LoginActivity.class);
        startActivity(LoginActivity);
    }

    public void loginUser(View view) {
        try {

           String nombre = nametext.getText().toString();
           String phone = phonetext.getText().toString();
           String direccion = direccionTxt.getText().toString();
           String mascota = mascotaTxt.getText().toString();
           String mail = mailTxt.getText().toString();
           String dia = diaTxt.getText().toString();
           String password = passwordtxt.getText().toString();
           String repetPassword= repitPasswordtxt.getText().toString();


        //    AlarmManager alarm= (AlarmManager) getSystemService(ALARM_SERVICE);
            //set timer you want alarm to work (here I have set it to 7.20pm)
          //  Calendar dayOfNotification = Calendar.getInstance();
            //long time= System.currentTimeMillis();
            //long timeseconf=1000*10;
             //dayOfNotification.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
             //long time2=dayOfNotification.getTimeInMillis();
            //Date after adding the days to the given date

            //Displaying the new Date after addition of Days


           // alarm.set(AlarmManager.RTC_WAKEUP,time2,pending);
            //alarm.set(AlarmManager.RTC_WAKEUP, 60000, pending);


           if (!Validation.IsEmptyRegister(nombre, direccion, phone, dia, mail,password)) {
                Toast.makeText(this,R.string.requeridField, Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Validation.ValidatePassword(password,repetPassword)) {
                Toast.makeText(this,R.string.passwordEqual, Toast.LENGTH_SHORT).show();
                return;

            }

            if (!Validation.isEmailValid(mail)) {
                Toast.makeText(this,R.string.emailIsInvalid, Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Validation.IsNumeric(dia)) {
                Toast.makeText(this, R.string.errorDia, Toast.LENGTH_SHORT).show();
                return;
            }
           // createUserRoom(nombre, phone, direccion, mascota, alimento, mail,dia,password);

            insertUserDataBase(nombre, phone, direccion, mascota, mail,dia,password);


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void GetBrand()
    {
        try {

           GetBrand brand = new GetBrand(this,spinnerBrand);
           brand.execute();
        }
        catch (Exception e) {
          Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void GetBreed()
    {
        try {

            GetBreedAsync breed = new GetBreedAsync(this,breedSpinner);
            breed.execute();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void GetBrandRoom()
    {
        try {

            // Get a new or existing ViewModel from the ViewModelProvider.
            brandViewModel = new ViewModelProvider(this).get(BrandViewModel.class);
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

        private void insertUserDataBase(String nombre,String telefono,String direccion,String mascota,String mail ,String dia,String password)
        {
            try {


               UserModel user = new UserModel(0,nombre,mail, parseInt(dia),productItem, password, mascota,telefono,direccion,brandItem,breedItem);

               backGround bc = new backGround(this,user);
               // bc.execute(user.getName().toString(), user.getTelephone().toString(), user.getAdress().toString() , user.getProduct().getIdProduct().toString(), user.getMail().toString(),user.getPassword().toString(),
                    //    String.valueOf(user.getDiasCount()),user.getPet(),String.valueOf( brandItem.idBrand),String.valueOf(breedItem.breedId) );
                bc.execute();
            }
            catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        private void createUserRoom(String nombre,String phone,String direccion,String mascota,String alimento,String mail,String dia,String password)
        {
            try {
                    //usuario = new user(nombre, phone, direccion, mascota, alimento, mail,password);
                   // usuarioViewModel.insert(usuario);
                }
              catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }

        private void GetProductByBrand(Integer idBrand)
        {
            try {

                GetProductByIdBrand prod = new GetProductByIdBrand(this,spinnerProduct);
                prod.execute(idBrand.toString());
            }
            catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()) {

            case  R.id.brandSpinner: // for item 1
                this.position = position;

                selection = parent.getItemAtPosition(position).toString();
                brandItem =(BrandModel)parent.getSelectedItem();
                GetProductByBrand(brandItem.idBrand);

              //
                //  Toast.makeText(getApplicationContext(), categoriesList.get(position).getId() + " Selected" , Toast.LENGTH_LONG).show();

                break;
            case R.id.productSpinner:
              productItem=  (ProductModel)parent.getSelectedItem();
                break;

            case R.id.breedSpinner:
                breedItem=  (BreedModel)parent.getSelectedItem();
                break;

            /* you can have any number of case statements */
            default :

        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}











