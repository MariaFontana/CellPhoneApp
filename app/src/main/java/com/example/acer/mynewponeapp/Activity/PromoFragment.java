package com.example.acer.mynewponeapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.mynewponeapp.Bussines.PromoBussiness;
import com.example.acer.mynewponeapp.DataBase.GetProduct;
import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.Util.verticalSpacignDecorator;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PromoFragment extends Fragment {

    private PromoAdapter promoAdapter;
    RecyclerView recycleViewPromo;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_promo_horizontal, container, false);
        recycleViewPromo = (RecyclerView) view.findViewById(R.id.recycleViewPromo);


        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewPromo.setLayoutManager(linear);

        recycleViewPromo.setHasFixedSize(true);



        verticalSpacignDecorator spacingRecicler = new verticalSpacignDecorator(1);
        recycleViewPromo.addItemDecoration(spacingRecicler);

        PromoBussiness promoBussiness= new PromoBussiness(getContext(),recycleViewPromo);
        promoBussiness.GetPromo();

        return view;
        // use a linear layout manager

    }


}
