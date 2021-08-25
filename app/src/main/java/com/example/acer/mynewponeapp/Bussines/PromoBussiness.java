package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IPromoBussines;
import com.example.acer.mynewponeapp.DataBase.PromoAsync;
import com.example.acer.mynewponeapp.Model.PromoModel;

import java.util.List;

public class PromoBussiness implements IPromoBussines {

    private Context context;
    private RecyclerView recycleViewPromo;

    public PromoBussiness(Context context,RecyclerView recycleViewPromo)
    {
        this.context=context;
        this.recycleViewPromo=recycleViewPromo;
    }

    @Override
    public List<PromoModel> GetPromo() {

        PromoAsync getPromo= new PromoAsync(context,recycleViewPromo);
        getPromo.execute();



        return null;
    }
}
