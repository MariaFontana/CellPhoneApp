package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Activity.ActivityHome;
import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Bussines.UpdateNotificaionBussines;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;


public class UpdateNotificationAsync extends AsyncTask<Void,Void,String> {

    private UpdateNotificationModel updateNotificationModel;
    private Context contextService;
    private StringBuilder  sb;
    String json = "";
    boolean IsParse=false;
    static JSONArray userJsonArray = null;

    //flag 0 means get and 1 means post.(By default it is get.)
    public UpdateNotificationAsync(Context context, UpdateNotificationModel updateNotificationModel ) {
        contextService = context;
        this.updateNotificationModel=updateNotificationModel;
    }


    @Override
    protected String doInBackground(Void... voids) {
        try {

            if (updateNotificationModel != null) {

                if(updateNotificationModel.getDateUpdate()==null)
                {
                  updateNotificationModel.setDateUpdate(new Date());
                }

                String dateUpdate = updateNotificationModel.getDateUpdate().toString();

                String countDay = updateNotificationModel.getCountDays().toString();

                String idUser = String.valueOf(updateNotificationModel.getUserModel().getIdUser());

                String link = "http://192.168.0.114:8080/updateNotification.php";

                String data = URLEncoder.encode("countDay", "UTF-8") + "=" +
                        URLEncoder.encode(countDay, "UTF-8");
           //     data += "&" + URLEncoder.encode("dateUpdate", "UTF-8") + "=" +
             //           URLEncoder.encode(dateUpdate, "UTF-8");
                data += "&" + URLEncoder.encode("idUser", "UTF-8") + "=" +
                        URLEncoder.encode(idUser, "UTF-8");


                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                     sb.toString();

                }
            }
        }

        catch (Exception e) {

            return new String("");
        }

        return sb.toString();
    }




   // private Boolean parse()
  //  {
   //     try
     //   {

       //     JSONObject updateNotificationJson;

        //    for (int i=0;i< userJsonArray.length();i++)
        //    {
        //        updateNotificationJson=userJsonArray.getJSONObject(i);

          //      String idUpdateNotification =updateNotificationJson.getString("idUpdateNotification");
          //      String dateUpdate =updateNotificationJson.getString("dateUpdate");
          //      String countDay = updateNotificationJson.getString("countDay");
          //      String idUser = updateNotificationJson.getString("idUser");
          //      Date fecha =new Date(dateUpdate);

            //    updateNotificationModel=new UpdateNotificationModel(BigInteger.valueOf(Long.parseLong(idUpdateNotification)) , fecha, Integer.parseInt(countDay));

         //       return IsParse=true;

        //    }

      //      return IsParse=false;

     //   } catch (JSONException e) {
      //      e.printStackTrace();
      //      return IsParse=false;
      //  }
   // }
    protected void onPostExecute(String result){

        if (result.isEmpty())
        {
            Toast.makeText(contextService, result, Toast.LENGTH_SHORT).show();
        }
        else {

            GetUpdateNotificationAsync updateAsync= new GetUpdateNotificationAsync(contextService);
            updateAsync.execute();

            contextService.startActivity(new Intent(contextService, ActivityHome.class));

        }

    }

}

