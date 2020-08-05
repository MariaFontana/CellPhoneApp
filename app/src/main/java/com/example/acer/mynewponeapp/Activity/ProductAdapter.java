package com.example.acer.mynewponeapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> productModelList;
    private Context context;

    public ProductAdapter(List<ProductModel> productModelList, Context context)
    {
        this.productModelList=productModelList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_product_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = productModelList.get(position).getName();
        double precio = productModelList.get(position).getPrecio();
        String description = productModelList.get(position).getDescription();
        String urlImage = productModelList.get(position).getPhotoId();
        holder.description.setText(description);
        holder.precio.setText(String.valueOf(precio));

        Picasso.with(context).load(urlImage).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView precio;
        private TextView description;
        ImageView photo;
        public ViewHolder(View v) {
            super(v);

            precio = (TextView) v.findViewById(R.id.textPrecio);
            description = (TextView) v.findViewById(R.id.txtDescription);
            photo = ( ImageView) v.findViewById(R.id.imageProduct);

        }
    }

}
