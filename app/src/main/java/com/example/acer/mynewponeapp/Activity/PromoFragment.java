package com.example.acer.mynewponeapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.mynewponeapp.R;

import androidx.fragment.app.Fragment;

public class PromoFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_promo_horizontal, container, false);
        return view;

    }


}
