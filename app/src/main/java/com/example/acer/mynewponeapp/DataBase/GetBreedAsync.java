package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.Model.BreedModel;
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

import androidx.appcompat.widget.AppCompatSpinner;

import static com.example.acer.mynewponeapp.DataBase.BussinessMysql.listJsonArray;

public class GetBreedAsync extends AsyncTask<String,Void,String>

    {

        Context contextService;
        static JSONArray brandJsonArray = null;
        String json = "";
        AppCompatSpinner spinnerBread;
        boolean IsParse=false;
        List<BreedModel> breadList =new ArrayList<>();
        ProgressDialog progressDialog;

        //flag 0 means get and 1 means post.(By default it is get.)
    public GetBreedAsync(Context context, AppCompatSpinner spinnerBread )
        {
            contextService = context;
            this.spinnerBread=spinnerBread;

            progressDialog= new ProgressDialog(contextService);
        }

        @Override
        protected String doInBackground(String... strings) {
        try
        {
            String link = constant.url+"/getBreed.php";

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
            new String("Exception: " + e.getMessage());

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


        public List<BreedModel> parse()
        {
            try
            {
                JSONObject brandJson;

                breadList.add(new BreedModel(0,"Selecciona una Mordida"));

                for (int i=0;i< brandJsonArray.length();i++)
                {
                    brandJson =brandJsonArray.getJSONObject(i);
                    BreedModel breedModelItem=new BreedModel();

                    breedModelItem.breedId = Integer.parseInt(brandJson.getString("idBreed")) ;
                    breedModelItem.name =brandJson.getString("name");

                    breadList.add(breedModelItem);

                }

                IsParse=true;


            } catch (JSONException e) {
                e.printStackTrace();
                IsParse=false;
            }
            return breadList;
        }

        @Override
        protected void onPostExecute(String result){

        ArrayAdapter<BreedModel> comboAdapterSql;

        super.onPostExecute(result);
        if( IsParse) {
            //Implemento el adapter con el contexto, layout, listaPaisesSql
            comboAdapterSql = new ArrayAdapter<>(contextService, android.R.layout.simple_spinner_item, breadList);
            //Cargo el spinner con los datos
            spinnerBread.setAdapter(comboAdapterSql);
            spinnerBread.getItemAtPosition(0);
        }
        else
        {
            Toast.makeText(contextService, "No se cargo la mordida ", Toast.LENGTH_SHORT).show();
        }

    }

    }


