package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Activity.ActivityHome;
import com.example.acer.mynewponeapp.Activity.ListProductActivity;
import com.example.acer.mynewponeapp.Bussines.NotificaionBussines;
import com.example.acer.mynewponeapp.Bussines.Session;

import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.Util.constant;

import static java.lang.Integer.parseInt;

public class backGround extends AsyncTask<Void,Void,String> {
    boolean running;
    ProgressDialog progressDialog;
    Context contextService;
    private Session session;
    private UserModel userModel;
    StringBuilder  sb;
    private UpdateNotificationAsync updateNotificationAsync;

    //flag 0 means get and 1 means post.(By default it is get.)
    public backGround(Context context,UserModel userModel ) {
        contextService = context;
        progressDialog= new ProgressDialog(contextService);
        this.userModel=userModel;
    }



    protected void onPreExecute() {
        this.progressDialog.setMessage("Loading...");
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... param) {

            try {

                if (userModel!=null) {

                    String name = userModel.getName().toString();
                    String telephone = userModel.getTelephone().toString();
                    String address = userModel.getAdress().toString();
                    String idProduct = userModel.getProduct().getIdProduct().toString();
                    String mail = userModel.getMail().toString();
                    String password = userModel.getPassword().toString();
                    String dateCount =  String.valueOf(userModel.getDiasCount());
                    String pet = userModel.getPet().toString();
                    String idBrand = String.valueOf(userModel.getBrandItem().getIdBrand());
                    String idBreed = String.valueOf(userModel.getBreedItem().getBreedId());


                   final String link = constant.url +"/insertUser2.php";

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

                    sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                }

                return sb.toString();
            }

            catch (Exception e) {

                return new String("");
            }
        }

    @Override
    protected void onPostExecute(String result){

        if(result.isEmpty())
        {
            Toast.makeText(contextService, R.string.errorConexion, Toast.LENGTH_LONG).show();
        }
        if (result.equals(new String("0")))
        {
            Toast.makeText(contextService, R.string.userExist, Toast.LENGTH_LONG).show();
        }
        else if(userModel!=null)  {

            int idUser=   Integer.parseInt(result) ;
            userModel.setIdUser(idUser);
            session=new Session(contextService);
            session.saveUserModel(userModel);
            Toast.makeText(contextService, R.string.userSave, Toast.LENGTH_LONG).show();
            UpdateNotificationModel updateNotificationModelNew= new UpdateNotificationModel(null, Calendar.getInstance().getTime(),userModel.getDiasCount(),userModel);
            NotificaionBussines noti= new NotificaionBussines(contextService,updateNotificationModelNew);
            noti.updateNotification();
           // contextService.startActivity(new Intent(contextService, ActivityHome.class));


        }
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }

    }


}

