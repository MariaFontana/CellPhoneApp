package com.example.acer.mynewponeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.lang.UProperty;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.DataBase.GetProduct;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProduct;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Session sessionUser ;
    private String mailUser;
    private String passwordUser;

    private CoordinatorLayout mCLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        sessionUser = new Session(this);

        if (!sessionUser.ValidateUserSession()) {

            this.startActivity(new Intent(this, LoginActivity.class));

        } else {

            this.mailUser = mailUser;
            this.passwordUser = passwordUser;

            setContentView(R.layout.activity_list_product);

            try {

                setValuesToolbar();

                mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

                CollapsingToolbarLayout collapsingToolbarLayout =
                        (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

               // collapsingToolbarLayout.setTitle("Hola" +" " + sessionUser.getNameUser() );


               // collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
             //   collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

                recyclerViewProduct = (RecyclerView) findViewById(R.id.recycleViewProduct);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerViewProduct.setHasFixedSize(true);

                // use a linear layout manager
                recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));


                GetProduct ListProduct = new GetProduct(this, recyclerViewProduct);
                ListProduct.execute();

                // specify an adapter with the list to show
                //  mAdapter = new ProductAdapter(getData());
                //  recyclerViewProduct.setAdapter(mAdapter);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
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

        toolbar.setTitle("Hola" + " "  + sessionUser.getNameUser());
        toolbar.setSubtitle("El último dia de compra en animalias fue 13-12-03");
        toolbar.setSubtitleTextColor(this.getResources().getColor(R.color.ColorAccent));

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
        sessionUser.setUserName("");
        sessionUser.setPassword("");
        sessionUser.SaveSharedPreferencesLogin();
        Intent LoginActivity = new Intent(this, LoginActivity.class);
        startActivity(LoginActivity);
    }


    // create fake data for this example
   // public List<ProductModel> getData() {

   //     GetProduct ListProduct = new GetProduct(this);
      //  ListProduct.execute();

     //   List<ProductModel> productModels = new ArrayList<>();
      //  productModels.add(new ProductModel("Royal Cannin",180.0,"miniadukto",1));
     //   productModels.add(new  ProductModel("Royal Cannin",180.0,"miniadukto",1));
     //   productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
      //  productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
      //  productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
      //  productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
     //   for(int i = 1; i < 10; i++) {
          //  productModels.add(new ProductModel("Name " + i, 180.0,"descripcion " +i,i ));
        //}

      //  return productModels;
    //}

}