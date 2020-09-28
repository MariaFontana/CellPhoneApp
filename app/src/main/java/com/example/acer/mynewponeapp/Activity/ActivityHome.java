package com.example.acer.mynewponeapp.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.R;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  DrawerLayout drawer;
    private  ActionBarDrawerToggle toggle;
    private Session sessionUser ;
    private Bundle arguments;
    private ProductModel productModel;
    private UserModel userModel;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        sessionUser = new Session(this);

        if (!sessionUser.ValidateUserSession()) {

            this.startActivity(new Intent(this, LoginActivity.class));
        }
        else {
            
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);

                drawer = findViewById(R.id.drawer_layout);

                toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


            //First start (Inbox Fragment)
            setFragment(0);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                menuItem.setChecked(true);
                setFragment(0);
                drawer.closeDrawer(GravityCompat.START);
            case R.id.updateDateBuy:
              //  menuItem.setChecked(true);
                //setFragment(1);
                //drawer.closeDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                PerfilFragment fragment = new PerfilFragment();
                //fragment.setProductModel(productModel);
                //fragment.setUserModel(userModel);
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.commit();
                break;
            case 1:
                //fragmentManager = getSupportFragmentManager();
               // fragmentTransaction = fragmentManager.beginTransaction();
                //StarredFragment starredFragment = new StarredFragment();
                //fragmentTransaction.replace(R.id.fragment, starredFragment);
                //fragmentTransaction.commit();
                break;
        }
    }
}
