package com.example.acer.mynewponeapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.acer.mynewponeapp.Bussines.Session;
import com.example.acer.mynewponeapp.DataBase.GetNotificationByUserAsync;
import com.example.acer.mynewponeapp.DataBase.GetUserByLogin;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;
import com.example.acer.mynewponeapp.R;
import com.example.acer.mynewponeapp.Util.verticalSpacignDecorator;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationListFragment extends Fragment {
    Session sessionUser;
    RecyclerView reciclerNotificationFragment;
    UserModel userModel;
    List<UpdateNotificationModel> updateNotificationModelList;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        reciclerNotificationFragment= (RecyclerView) view.findViewById(R.id.recyclerViewNotification);

        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        reciclerNotificationFragment.setLayoutManager(linear);

        reciclerNotificationFragment.setHasFixedSize(true);

        verticalSpacignDecorator spacingRecicler= new verticalSpacignDecorator(1);
        reciclerNotificationFragment.addItemDecoration(spacingRecicler);

        sessionUser = new Session(getContext());

        updateNotificationModelList =sessionUser.GetUserModel().getNotificationModelList();

        GetData();

        return view;
    }


    private void GetData()
    {

        NotificationAdapter notificator=new NotificationAdapter(updateNotificationModelList,getContext());
        reciclerNotificationFragment.setAdapter(notificator);
    }


}
