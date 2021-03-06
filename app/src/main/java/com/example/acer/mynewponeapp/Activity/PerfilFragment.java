package com.example.acer.mynewponeapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.mynewponeapp.Bussines.PerfilFragmentBusiness;
import com.example.acer.mynewponeapp.Bussines.ProductBussnes;
import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.fragment.app.Fragment;


public class PerfilFragment extends Fragment {

    private UserModel userModel;
    private Session session;
    private UpdateNotificationModel updateNotificationModel;
    ProductBussnes productBussnes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        session = new Session(getContext());

        this.userModel  =session.GetUserModel();
        GetUpdateNotification();
        // Inflate the layout for this fragment

    //    GetUserModel();
        //GetUpdateNotification();
       GetProductBussiness();

       long days= productBussnes.CalculationDurationFeed();

        final View view = inflater.inflate(R.layout.perfil_fragment, container, false);
        final TextView precio = (TextView) view.findViewById(R.id.textPrecio);
        final TextView duration = (TextView) view.findViewById(R.id.duration);
        final TextView dateBuy = (TextView) view.findViewById(R.id.dateBuy);
        final TextView description = (TextView) view.findViewById(R.id.txtDescription);

        ImageView photo = (ImageView)view.findViewById(R.id.imageProduct);

        final TextView daysRemaining =(TextView)view.findViewById(R.id.textDaysRemaining);

        precio.setText(userModel.getProduct().getPrecio().toString());
        String urlImage =userModel.getProduct().getPhotoId().toString();
        Picasso.with(getContext()).load(urlImage).into(photo);
        description.setText(userModel.getProduct().description.toString());
        Date dateNew= updateNotificationModel.getDateUpdate();
        //fecha de hoy
        Calendar calendarNow = Calendar.getInstance();
       long starTime= dateNew.getTime();
       long finishTime= calendarNow.getTime().getTime();

        long diffTime = finishTime -starTime;
        long diffDays = diffTime / (1000 * 60 * 60 * 24);

        daysRemaining.setText("Te quedan " + diffDays + " días de alimento");

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(dateNew);
        dateBuy.setText("última compra " + formattedDate);
        duration.setText("Duración alimento" + updateNotificationModel.getCountDays() + " días" );

        return view;
    }

    public void GetUserModel()
    {
        PerfilFragmentBusiness perfilFragmentBusiness =new PerfilFragmentBusiness(getContext(),session);

        //perfilFragmentBusiness.GetUserModel();
       // this.userModel=session.GetUserModel();
    }

    public void GetUpdateNotification()
    {
        if(session.GetUserModel().getListNotificationModelList().size() != 0) {
            this.updateNotificationModel = session.GetUserModel().getListNotificationModelList().get(0);
        }

        PerfilFragmentBusiness perfil=new PerfilFragmentBusiness(getContext(),session);
        perfil.GetUpdateNotification();
    }
    public void GetProductBussiness()
    {
        productBussnes= new ProductBussnes(userModel);

    }
}
