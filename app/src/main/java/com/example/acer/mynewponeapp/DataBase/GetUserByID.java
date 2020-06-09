package com.example.acer.mynewponeapp.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetUserByID extends AsyncTask< String ,Void,String> {

    Context contextService;

    //flag 0 means get and 1 means post.(By default it is get.)
    public GetUserByID(Context context) {
        contextService = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            String idUsuario = strings[0];
            String link = "http://192.168.0.104:8080/conectionDataBase.php";


            String data = URLEncoder.encode("idUsuario", "UTF-8") + "=" +
                    URLEncoder.encode(idUsuario, "UTF-8");

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
}