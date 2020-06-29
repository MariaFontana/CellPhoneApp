package com.example.acer.mynewponeapp.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetUserByLogin extends AsyncTask< String ,Void,String>
{

    Context contextService;


    public GetUserByLogin(Context context) {
        contextService = context;
    }


    @Override
    protected String doInBackground(String... strings) {

        try {
            String mail = strings[0];
            String password = strings[1];


            String link = "http://192.168.0.109:8080/getUserLogin.php";


            String data = URLEncoder.encode("mail", "UTF-8") + "=" +
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
                sb.append(line + "\n");

                sb.toString();
            }


            return null;
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());

        }
    }

    @Override
    protected void onPostExecute(String result){

        Toast.makeText(contextService,   "Redirecting...", Toast.LENGTH_SHORT).show();
    }
}