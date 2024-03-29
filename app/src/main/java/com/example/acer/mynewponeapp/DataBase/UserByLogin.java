package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.Util.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class UserByLogin extends AsyncTask< String ,Void,String>
{
    ProgressDialog progressDialog;
    Context contextService;
    String  result1="";
    private Session session;
    private String mailUser;
    private String passwordUser;
    private String nombreUser;
    static JSONArray userJsonArray = null;

    UserModel  userModel ;
    ProductModel product;
    UpdateNotificationModel updateNotificationModel;
    String json = "";
    boolean IsParse=false;

    public UserByLogin(Context context, String mailUser, String passwordUser) {
        contextService = context;
        progressDialog= new ProgressDialog(contextService);
        this.passwordUser=passwordUser;
        this.mailUser=mailUser;
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


           // String link = constant.url+"/getUserLogin.php";
            String link = constant.url+"/php/getUserLogin.php";

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


            while ((line = reader.readLine()) != null) {
                sb.append(line);

                sb.append(line + "\n");

                json = sb.toString();

                userJsonArray   = CreateJson(json);
                parse();

               // userJsonArray = CreateJson(json);
             //   parse();
            }



        }
        catch(Exception e){return new String("Exception: " + e.getMessage());


        }
        return userJsonArray.toString();
    }



    protected JSONArray CreateJson(String json) {
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


            JSONObject UserJson;


            for (int i=0;i< userJsonArray.length();i++)
            {
                UserJson=userJsonArray.getJSONObject(i);

                String name=UserJson.getString("nameUser");
                String password =UserJson.getString("password");
                String mail = UserJson.getString("mail");
                String productName = UserJson.getString("product");
                String productPrecio = UserJson.getString("precio");
                String productImage = UserJson.getString("photoId");
                String productDescription=UserJson.getString("description");
                String idUser=UserJson.getString("idUser");
                String idProduct=UserJson.getString("idProduct");
                String idCategory=UserJson.getString("idCategory");



                product=new ProductModel(Integer.parseInt(idProduct) ,productName,Double.parseDouble(productPrecio),productDescription,0,productImage,null,Integer.parseInt(idCategory) );

                userModel= new UserModel(Integer.parseInt(idUser) ,name,mail,password,product);

                return IsParse=true;

            }

            return IsParse;

        } catch (JSONException e) {
            e.printStackTrace();
            return IsParse=false;
        }
    }


    @Override
    protected void onPostExecute(String result) {

        try {
            if (IsParse) {

                session = new Session(contextService);

                session.saveProductModel(product);
                //Save User
                session.saveUserModel(userModel);

                NotificationByUserAsync getNotification = new NotificationByUserAsync(contextService);
                getNotification.execute();

              //  contextService.startActivity(new Intent(contextService, ActivityHome.class));



            } else if (!IsParse) {

                Toast.makeText(contextService, "El usuario y la contraseña no son válidos", Toast.LENGTH_SHORT).show();
                if (this.progressDialog.isShowing()) {
                    this.progressDialog.dismiss();
                }
            }
        }
        catch (Exception e){
             new String("Exception: " + e.getMessage());

        }
    }


    }


