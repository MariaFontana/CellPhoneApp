package com.example.acer.mynewponeapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Model.PromoModel;
import com.example.acer.mynewponeapp.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;


    public class PromoAdapter extends RecyclerView.Adapter<com.example.acer.mynewponeapp.Activity.PromoAdapter.ViewHolder> {

        private List<PromoModel> promoModelList;
        private Context context;

        public PromoAdapter(List<PromoModel> promoModelModelList, Context context)
        {
            this.promoModelList=promoModelModelList;
            this.context=context;
        }

        @Override
        public PromoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_promo_item_horizontal, parent, false);
            PromoAdapter.ViewHolder viewHolder = new PromoAdapter.ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PromoAdapter.ViewHolder holder, int position) {


            Date startPromo = promoModelList.get(position).getStartPromo();
            Date finishPromo = promoModelList.get(position).getFinishPromo();
            String urlImage = promoModelList.get(position).getImage();


            Picasso.with(context).load(urlImage).into(holder.photo);

        }


        @Override
        public int getItemCount() {
            return promoModelList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            ImageView photo;
            public ViewHolder(View v) {
                super(v);
                photo = ( ImageView) v.findViewById(R.id.imagePromo);

            }
        }


    }
