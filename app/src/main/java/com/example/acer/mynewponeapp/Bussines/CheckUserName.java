package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class CheckUserName extends AsyncTask< String ,Void,String> {

    private String userName;
    Context contextService;
    private String resp;

    public CheckUserName(String mail, Context context) {
        contextService = context;
        userName = mail;
    }



    @Override
    protected String doInBackground(String... strings) {

        try {
            String link = "http://192.168.0.109:8080/getUserLogin.php";


            String data = URLEncoder.encode("mail", "UTF-8") + "=" +
                    URLEncoder.encode(userName, "UTF-8");


            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String json;

            //reading until we don't find null
            while ((json = reader.readLine()) != null) {

                //appending it to string builder
                sb.append(json + "\n");
            }

            //finally returning the read string
            return sb.toString().trim();



            }

        catch(Exception e){
                return new String("Exception: " + e.getMessage());

            }

        }

    @Override
    protected void onPostExecute(String result){
        if(result != "") {
            Toast.makeText(contextService, "El Usuario ya Existe", Toast.LENGTH_SHORT).show();
        }
    }
    }


