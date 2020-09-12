package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Activity.ListProductActivity;
import com.example.acer.mynewponeapp.Bussines.Session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class backGround extends AsyncTask<String,Void,String> {
    boolean running;
    ProgressDialog progressDialog;
    Context contextService;
    private Session session;
    private String mailUser;
    private String passwordUser;
    private String nombreUser;

    //flag 0 means get and 1 means post.(By default it is get.)
    public backGround(Context context,String mailUser,String passwordUser,String nombreUser ) {
        contextService = context;
        progressDialog= new ProgressDialog(contextService);
        this.passwordUser=passwordUser;
        this.mailUser=mailUser;
        this.nombreUser=nombreUser;

    }

    protected void onPreExecute() {
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }
    @Override


    protected String doInBackground(String... strings) {

            try {

                String name = strings[0];
                String telephone = strings[1];
                String address = strings[2];
                String idProduct = strings[3];
                String mail = strings[4];
                String password = strings[5];
                String dateCount = strings[6];
                String pet = strings[7];
                String idBrand=strings[8];
                String idBreed=strings[9];




                String link = "http://192.168.0.114:8080/insertUser2.php";

                String data = URLEncoder.encode("name", "UTF-8") + "=" +
                        URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("telephone", "UTF-8") + "=" +
                        URLEncoder.encode(telephone, "UTF-8");
                data += "&" + URLEncoder.encode("address", "UTF-8") + "=" +
                        URLEncoder.encode(address, "UTF-8");
                data += "&" + URLEncoder.encode("pet", "UTF-8") + "=" +
                        URLEncoder.encode(pet, "UTF-8");
                data += "&" + URLEncoder.encode("dateCount", "UTF-8") + "=" +
                        URLEncoder.encode(dateCount, "UTF-8");
                data += "&" + URLEncoder.encode("mail", "UTF-8") + "=" +
                        URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");
                data += "&" + URLEncoder.encode("idBrand", "UTF-8") + "=" +
                        URLEncoder.encode(idBrand, "UTF-8");
                data += "&" + URLEncoder.encode("idBreed", "UTF-8") + "=" +
                        URLEncoder.encode(idBreed, "UTF-8");
                data += "&" + URLEncoder.encode("idProduct", "UTF-8") + "=" +
                        URLEncoder.encode(idProduct, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();

            } catch (Exception e) {

                return new String("");
            }
        }





    @Override
    protected void onPostExecute(String result){

        if (result.isEmpty())
        {
            Toast.makeText(contextService, result, Toast.LENGTH_SHORT).show();
        }
        else {

            session=new Session(contextService,mailUser,passwordUser,nombreUser);
            session.SaveSharedPreferencesLogin();
            Toast.makeText(contextService, result, Toast.LENGTH_SHORT).show();
            contextService.startActivity(new Intent(contextService, ListProductActivity.class));
        }
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }

    }


}

