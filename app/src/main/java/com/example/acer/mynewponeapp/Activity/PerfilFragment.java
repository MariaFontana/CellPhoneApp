package com.example.acer.mynewponeapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.R;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;


public class PerfilFragment extends Fragment {

    private UserModel userModel;
    private Session session;

    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        session = new Session(getContext());
        // Inflate the layout for this fragment

        GetUserModel();

        final View view = inflater.inflate(R.layout.perfil_fragment, container, false);

        final TextView precio = (TextView) view.findViewById(R.id.textPrecio);

        final TextView description = (TextView) view.findViewById(R.id.txtDescription);

        ImageView photo = (ImageView)view.findViewById(R.id.imageProduct);

        precio.setText(userModel.getProduct().getPrecio().toString());
        String urlImage =userModel.getProduct().getPhotoId().toString();
        Picasso.with(getContext()).load(urlImage).into(photo);
        description.setText(userModel.getProduct().description.toString());

        return view;
    }

    public void GetUserModel() {
         this.userModel = session.GetUserModel();
    }
}
