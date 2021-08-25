package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;

import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel.BrandViewModel;
import com.example.acer.mynewponeapp.Util.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static com.example.acer.mynewponeapp.DataBase.BussinessMysql.listJsonArray;

public class BrandAsync extends AsyncTask<String,Void,String>  {

    Context contextService;
    static JSONArray brandJsonArray = null;
    String json = "";
    AppCompatSpinner spinnerBrand;

    boolean IsParse=false;
    List<BrandModel>brandList =new ArrayList<>();
    ProgressDialog progressDialog;
    BrandViewModel  brandViewModel;

    //flag 0 means get and 1 means post.(By default it is get.)
    public BrandAsync(Context context,AppCompatSpinner spinnerBrand )
    {
        contextService = context;
        this.spinnerBrand=spinnerBrand;
        progressDialog= new ProgressDialog(contextService);
    }

    @Override
    protected String doInBackground(String... strings) {
        try
        {
            String link = constant.url+"/php/getBrand.php";

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

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

            json = sb.toString();

            brandJsonArray   = CreateJson(json );

            parse();



        }

         catch (Exception e) {
             System.out.println("problemas con la conexion!");

        }
        return null;
    }

    public JSONArray CreateJson(String json) {
        try {

            listJsonArray = new JSONArray(json);

        } catch (JSONException e) {
            listJsonArray=null;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return listJsonArray;
    }


    public List<BrandModel> parse()
    {
        try
        {
            JSONObject brandJson;

             brandList.add(new BrandModel(0,"Selecciona una Marca"));


            for (int i=0;i< brandJsonArray.length();i++)
            {
                brandJson =brandJsonArray.getJSONObject(i);
                BrandModel brandModelItem=new BrandModel();

                brandModelItem.idBrand = Integer.parseInt(brandJson.getString("idbrand")) ;
                brandModelItem.name =brandJson.getString("name");



                brandList.add(brandModelItem);

            }

            IsParse=true;


        } catch (JSONException e) {
            e.printStackTrace();
            IsParse=false;
        }
        return brandList;
    }

    @Override
    protected void onPostExecute(String result){

        ArrayAdapter<BrandModel> comboAdapterSql;
        super.onPostExecute(result);
        if( IsParse) {
            //Implemento el adapter con el contexto, layout, listaPaisesSql
          comboAdapterSql = new ArrayAdapter<>(contextService, android.R.layout.simple_spinner_item, brandList);
            //Cargo el spinner con los datos
            spinnerBrand.setAdapter(comboAdapterSql);
            spinnerBrand.getItemAtPosition(0);
        }
        else
        {
            Toast.makeText(contextService, R.string.errorConexion, Toast.LENGTH_SHORT).show();
        }

    }
}
