package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Activity.ListProductActivity;

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
    ProgressDialog progressDialog;
    Context contextService;
    String  result1="";

    public GetUserByLogin(Context context) {
        contextService = context;
        progressDialog= new ProgressDialog(contextService);

    }


@Override
    protected void onPreExecute() {
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }
    @Override
    protected String doInBackground(String... strings) {

        try {
            String mail = strings[0];
            String password = strings[1];


            String link = "http://192.168.0.111:8080/getUserLogin.php";


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


               result1= sb.toString();
            }


            return result1;
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());

        }
    }

    @Override
    protected void onPostExecute(String result){
      if(result1 !="")
        {

            //super.onPostExecute(result);
            //Intent intent = new Intent(contextService, ListProductActivity.class);

            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contextService.startActivity(new Intent(contextService, ListProductActivity.class));
            //contextService.startActivity(intent);
        }
        else {
            Toast.makeText(contextService,   "El usuario no existe", Toast.LENGTH_SHORT).show();
            if (this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
        }


    }


}