package com.example.acer.mynewponeapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AccesorioAdapter extends RecyclerView.Adapter<com.example.acer.mynewponeapp.Activity.AccesorioAdapter.ViewHolder> {

    private List<ProductModel> productModelList;
    private Context context;

    public AccesorioAdapter(List<ProductModel> productModelList, Context context)
    {
        this.productModelList=productModelList;
        this.context=context;
    }



    @Override
    public AccesorioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_accesorio_item, parent, false);
        AccesorioAdapter.ViewHolder viewHolder = new AccesorioAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccesorioAdapter.ViewHolder holder, int position) {

        String urlImage = productModelList.get(position).getPhotoId();


        Picasso.with(context).load(urlImage).into(holder.photo);

    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        public ViewHolder(View v) {
            super(v);
            photo = ( ImageView) v.findViewById(R.id.imageAccesorio);

        }

    }
}
