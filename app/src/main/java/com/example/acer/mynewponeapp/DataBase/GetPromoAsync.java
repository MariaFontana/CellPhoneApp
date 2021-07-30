package com.example.acer.mynewponeapp.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Activity.PromoAdapter;
import com.example.acer.mynewponeapp.Model.PromoModel;
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

public class GetPromoAsync extends AsyncTask< String ,Void,String> {

    Context contextService;
    static JSONArray ProductJsonArray = null;
    String json = "";
    private RecyclerView.Adapter mAdapter;
    List<PromoModel> listPromo = new ArrayList<>();
    RecyclerView recyclerViewPromo;
    boolean IsParse=false;

    public GetPromoAsync(Context context, RecyclerView recyclerView) {
        contextService = context;
        this.recyclerViewPromo=recyclerView;

    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            String link = constant.url+"/php/GetPromo.php";

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
                sb.append(line + "\n");

                json = sb.toString();

                ProductJsonArray   = CreateJson();
                parse();
            }

        } catch (Exception e) {
            new String("Exception: " + e.getMessage());

        }

        return ProductJsonArray.toString();
    }


    protected JSONArray CreateJson() {
        try {

            ProductJsonArray = new JSONArray(json);

        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return ProductJsonArray;
    }

    private Boolean parse()
    {
        try
        {

            JSONObject productJson;

            for (int i=0;i< ProductJsonArray.length();i++)
            {
                productJson=ProductJsonArray.getJSONObject(i);
                int idPromo = Integer.parseInt(productJson.getString("idPromo"));
                String name=productJson.getString("name");
                String startPromo =productJson.getString("startPromo");
                String finishPromo = productJson.getString("finishPromo");
                String image =productJson.getString("image");

                PromoModel promo =new PromoModel(idPromo,name,startPromo,finishPromo,image);
                listPromo.add(promo);
            }

            return IsParse=true;

        } catch (JSONException e) {
            e.printStackTrace();
            return IsParse=false;
        }
    }

    @Override
    protected void onPostExecute(String result){
        try {
            super.onPostExecute(result);
            if (IsParse) {

                mAdapter = new PromoAdapter(listPromo, contextService);
                recyclerViewPromo.setAdapter(mAdapter);

            } else {
                Toast.makeText(contextService, "Unable To Parse,Check Your Log output", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e) {
            e.getMessage();
        }
    }

}
