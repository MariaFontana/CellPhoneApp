package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IPromoBussines;
import com.example.acer.mynewponeapp.DataBase.GetPromoAsync;
import com.example.acer.mynewponeapp.Model.PromoModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

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

        GetPromoAsync getPromo= new GetPromoAsync(context,recycleViewPromo);
        getPromo.execute();



        return null;
    }
}
