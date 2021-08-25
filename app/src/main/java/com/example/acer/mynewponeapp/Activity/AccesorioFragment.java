package com.example.acer.mynewponeapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Bussines.ProductBussines;
import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.Util.verticalSpacignDecorator;



public class AccesorioFragment extends Fragment {


    // TODO: Rename and change types of parameters

    RecyclerView recycleViewAccesorio;
    ProductBussines productBussines;

    public AccesorioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_accesorio_horizontal, container, false);
        recycleViewAccesorio = (RecyclerView) view.findViewById(R.id.recycleViewAccesorio);


        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        linear.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewAccesorio.setLayoutManager(linear);

        recycleViewAccesorio.setHasFixedSize(true);



        verticalSpacignDecorator spacingRecicler = new verticalSpacignDecorator(1);
        recycleViewAccesorio.addItemDecoration(spacingRecicler);

        ProductBussines productBussiness= new ProductBussines(recycleViewAccesorio,getContext());
        productBussiness.GetAccesorios();

        return view;
        // use a linear layout manager

    }


}

