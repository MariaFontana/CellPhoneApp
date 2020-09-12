package com.example.acer.mynewponeapp.DataBase;

import android.util.Log;

import com.example.acer.mynewponeapp.Model.BrandModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public  class BussinessMysql {

    List<String>ListItem =new ArrayList<>();
    JSONObject ObjJson;
    static JSONArray listJsonArray = null;
    String json = "";
    String idJson;
    String nameJson;
    public boolean IsParse=false;

    public BussinessMysql(String id,String name )
    {
        this.idJson=id;
        this.nameJson=name;

    }

    public BussinessMysql( )
    {


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

    public List<String> parse()
    {
        try
        {
            JSONObject ObjJson;

            for (int i=0;i< listJsonArray.length();i++)
            {
                ObjJson=listJsonArray.getJSONObject(i);

                String id=ObjJson.getString(idJson);
                String name =ObjJson.getString(nameJson);

                ListItem.add(name);
            }

            IsParse=true;


        } catch (JSONException e) {
            e.printStackTrace();
            IsParse=false;
        }
        return ListItem;
    }



}
