package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    //flag 0 means get and 1 means post.(By default it is get.)
    public backGround(Context context) {
        contextService = context;
    }

    protected void onPreExecute() {
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {

            try {



                String nombre = strings[0];
                String telephone = strings[1];
                String adress = strings[2];
                String mascota = strings[3];
                String alimento = strings[4];
                String mail = strings[5];
                String dia = strings[6];
                String password = strings[7];


                String link = "http://192.168.0.111:8080/insertUser2.php";


                String data = URLEncoder.encode("name", "UTF-8") + "=" +
                        URLEncoder.encode(nombre, "UTF-8");
                data += "&" + URLEncoder.encode("telephone", "UTF-8") + "=" +
                        URLEncoder.encode(telephone, "UTF-8");
                data += "&" + URLEncoder.encode("adress", "UTF-8") + "=" +
                        URLEncoder.encode(adress, "UTF-8");
                data += "&" + URLEncoder.encode("pet", "UTF-8") + "=" +
                        URLEncoder.encode(mascota, "UTF-8");
                data += "&" + URLEncoder.encode("alimento", "UTF-8") + "=" +
                        URLEncoder.encode(alimento, "UTF-8");
                data += "&" + URLEncoder.encode("dia", "UTF-8") + "=" +
                        URLEncoder.encode(dia, "UTF-8");
                data += "&" + URLEncoder.encode("mail", "UTF-8") + "=" +
                        URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");


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
                return new String("Exception: " + e.getMessage());
            }
        }





    @Override
    protected void onPostExecute(String result){
        Toast.makeText(contextService, "Los Datos fueron ", Toast.LENGTH_SHORT).show();
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
}

