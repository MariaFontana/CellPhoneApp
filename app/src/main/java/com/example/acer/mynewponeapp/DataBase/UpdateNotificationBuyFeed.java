package com.example.acer.mynewponeapp.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;


public class UpdateNotificationBuyFeed extends AsyncTask<String,Void,String>  {

    Context contextService;
    static JSONArray userJsonArray = null;
    UpdateNotificationModel updateNotificationModel ;
    String json = "";
    boolean IsParse=false;

    public UpdateNotificationBuyFeed(Context context ) {
        contextService = context;

    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {


        try {
            String idUser = strings[0];
            String countDays = strings[1];
            String date = strings[2];


            String link = "/php/insertUser2.php";

            String data = URLEncoder.encode("idUser", "UTF-8") + "=" +
                    URLEncoder.encode(idUser, "UTF-8");
            data += "&" + URLEncoder.encode("dateUpdate", "UTF-8") + "=" +
                    URLEncoder.encode(date, "UTF-8");

            data += "&" + URLEncoder.encode("countDay", "UTF-8") + "=" +
                    URLEncoder.encode(date, "UTF-8");


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

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(line + "\n");

                json = sb.toString();

                userJsonArray = CreateJson();
                parse();
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());

        }





        return null;
    }


    protected JSONArray CreateJson() {
        try {

            userJsonArray = new JSONArray(json);

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return userJsonArray;
    }


    private Boolean parse()
    {
        try
        {

            JSONObject updateNotificationJson;

            for (int i=0;i< userJsonArray.length();i++)
            {
                updateNotificationJson=userJsonArray.getJSONObject(i);

                String idUpdateNotification =updateNotificationJson.getString("idUpdateNotification");
                String dateUpdate =updateNotificationJson.getString("dateUpdate");
                String countDay = updateNotificationJson.getString("countDay");
                String idUser = updateNotificationJson.getString("idUser");
                Date fecha =new Date(dateUpdate);


              //  UpdateNotificationModel updateNotificatinModel=new UpdateNotificationModel(BigInteger.valueOf(Long.parseLong(idUpdateNotification)) , fecha, Integer.parseInt(countDay));


                return IsParse=true;

            }

            return IsParse=false;

        } catch (JSONException e) {
            e.printStackTrace();
            return IsParse=false;
        }
    }

}
