package com.example.acer.mynewponeapp.DataBase;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.Model.BreedModel;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Util.constant;

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
import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.widget.AppCompatSpinner;

import static com.example.acer.mynewponeapp.DataBase.BussinessMysql.listJsonArray;
import static java.lang.Integer.parseInt;

public class GetProductByIdBrand extends AsyncTask<String,Void,String>  {

    ProgressDialog progressDialog;
    Context contextService;
    static JSONArray productJsonArray = null;
    String json = "";
    AppCompatSpinner spinnerProduct;
    AppCompatSpinner breedSpinner;

    List<ProductModel>productList=new ArrayList<>();

    boolean IsParse=false;


    public GetProductByIdBrand(Context context, AppCompatSpinner spinnerProduct )
    {
        contextService = context;
        this.spinnerProduct=spinnerProduct;
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
            String idBrand = strings[0];
            String link = constant.url+"/getproductbybrand.php";

            String data = URLEncoder.encode("idBrand", "UTF-8") + "=" +
                    URLEncoder.encode(idBrand, "UTF-8");


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
                break;
            }

            json = sb.toString();
            productJsonArray = CreateJson(json);
            parse();

        }

        catch (Exception e) {
            productJsonArray=null;
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


    public List<ProductModel> parse()
    {
        try
        {
            JSONObject productJson;
            ProductModel productItemSelect= new ProductModel();
            BigInteger idProductSelect = new BigInteger("0");
            productItemSelect.idProduct=idProductSelect;
            productItemSelect.name="Seleccione un Producto";
            productList.add(productItemSelect);

            for (int i=0;i< productJsonArray.length();i++)
            {
                productJson =productJsonArray.getJSONObject(i);
                ProductModel productItem= new ProductModel();
                BigInteger idProduct = new BigInteger(productJson.getString("idProduct"));

                productItem.idProduct = idProduct ;
                productItem.name =productJson.getString("name");
                productItem.description=productJson.getString("description");
                productItem.precio= Double.parseDouble(productJson.getString("precio"));
                productItem.cantidad= Integer.parseInt(productJson.getString("cantidad"));
                productItem.idBrand=Integer.parseInt(productJson.getString("idBrand"));
                productItem.photoId=productJson.getString("image");
                productList.add(productItem);

            }

            IsParse=true;


        } catch (JSONException e) {
            e.printStackTrace();
            IsParse=false;
        }
        return productList;
    }
    @Override
    protected void onPostExecute(String result){

        ArrayAdapter<ProductModel> comboAdapterSql;
        ArrayAdapter<BreedModel> comboAdapterSqlBreed;

        super.onPostExecute(result);

        if( IsParse) {
            //Implemento el adapter con el contexto, layout, listaPaisesSql
            comboAdapterSql = new ArrayAdapter<ProductModel>(contextService, android.R.layout.simple_spinner_item,productList );
            //Cargo el spinner con los datos
            spinnerProduct.setAdapter(comboAdapterSql);

        }
        else
        {
            Toast.makeText(contextService, "error spinner producto", Toast.LENGTH_SHORT).show();
        }

        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }

    }




}
