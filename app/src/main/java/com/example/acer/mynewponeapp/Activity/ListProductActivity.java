package com.example.acer.mynewponeapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.lang.UProperty;
import android.os.Bundle;
import android.widget.Toast;

import com.example.acer.mynewponeapp.DataBase.GetProduct;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProduct;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        try {

            recyclerViewProduct = (RecyclerView) findViewById(R.id.recycleViewProduct);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerViewProduct.setHasFixedSize(true);

            // use a linear layout manager
            recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));

            GetProduct ListProduct = new GetProduct(this,recyclerViewProduct);
            ListProduct.execute();

            // specify an adapter with the list to show
          //  mAdapter = new ProductAdapter(getData());
          //  recyclerViewProduct.setAdapter(mAdapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    // create fake data for this example
   // public List<ProductModel> getData() {

   //     GetProduct ListProduct = new GetProduct(this);
      //  ListProduct.execute();

     //   List<ProductModel> productModels = new ArrayList<>();
      //  productModels.add(new ProductModel("Royal Cannin",180.0,"miniadukto",1));
     //   productModels.add(new  ProductModel("Royal Cannin",180.0,"miniadukto",1));
     //   productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
      //  productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
      //  productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
      //  productModels.add(new  ProductModel("Royal Cannin",180.00,"miniadukto",1));
     //   for(int i = 1; i < 10; i++) {
          //  productModels.add(new ProductModel("Name " + i, 180.0,"descripcion " +i,i ));
        //}

      //  return productModels;
    //}

}