package com.example.acer.mynewponeapp.DataBase;

import android.content.Context;
import android.content.Intent;

import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Time;
import java.time.Instant;
import java.util.Date;


import com.example.acer.mynewponeapp.Activity.ActivityHome;
import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Bussines.UpdateNotificaionBussines;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.Util.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;


public class GetUpdateNotificationAsync extends AsyncTask<Void,Void,String> {

private UserModel userModel;
private Context contextService;
private StringBuilder  sb;
private String json = "";
private boolean IsParse=false;
static JSONArray userJsonArray = null;
private UpdateNotificationModel updateNotificatinModel;
private Session session;
//flag 0 means get and 1 means post.(By default it is get.)
public GetUpdateNotificationAsync(Context context) {
            contextService = context;
            session= new Session(context);
            if(session!=null) {
                this.userModel = session.GetUserModel();
            }
        }


    @Override
    protected String doInBackground(Void... voids) {
        try {

        if (userModel != null) {

        String idUser = String.valueOf(userModel.getIdUser());

        String link = constant.url +"/getLastNotification.php";

        String data = URLEncoder.encode("idUser", "UTF-8") + "=" +
        URLEncoder.encode(idUser, "UTF-8");

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
        }

        catch (Exception e) {

        return new String("");
        }

        return parse().toString();
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


                              updateNotificatinModel=new UpdateNotificationModel(BigInteger.valueOf(Long.parseLong(idUpdateNotification)) , dateUpdate, Integer.parseInt(countDay));

                             return IsParse=true;

                    }

                    return IsParse=false;

        } catch (JSONException e) {
                    e.printStackTrace();
                     return IsParse=false;
                }
            }



        @Override
        protected void onPostExecute(String result) {

            session.saveUpdateNotidicationModel(updateNotificatinModel);

            UpdateNotificaionBussines updateNotificaionBussines = new UpdateNotificaionBussines(contextService,updateNotificatinModel);
            updateNotificaionBussines.CalculateAlarmNotification();
            contextService.startActivity(new Intent(contextService, ActivityHome.class));

        }

}
