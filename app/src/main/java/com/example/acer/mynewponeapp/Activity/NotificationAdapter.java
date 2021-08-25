package com.example.acer.mynewponeapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Bussines.ProductBussines;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<UpdateNotificationModel> updateNotificationModelsList;
    private Context context;
    private ProductBussines productBussines;
    public NotificationAdapter(List<UpdateNotificationModel> updateNotificationModelsList, Context context)
    {
        this.updateNotificationModelsList=updateNotificationModelsList;
        this.context=context;


    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_notification_item, parent, false);
        NotificationAdapter.ViewHolder viewHolder = new NotificationAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {

        Date dateUpdate= updateNotificationModelsList.get(position).getDateUpdate();
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(dateUpdate);
        String date = formattedDate;



        int  daysRemaining= updateNotificationModelsList.get(position).getCountDays();

        Calendar calendarNow = Calendar.getInstance();

        calendarNow.setTime(dateUpdate);

        calendarNow.add(Calendar.DAY_OF_YEAR, daysRemaining);

        Date dateFinish =calendarNow.getTime();

        String formattedNextDate = new SimpleDateFormat("dd/MM/yyyy").format(dateFinish);
        int colorUpdate=dateUpdate.compareTo(calendarNow.getTime());

        switch(colorUpdate) {
            case 0:
                holder.imageNotification.setImageResource(R.drawable.orange_add_shopping_cart_24);

                break;
            case -1:
                holder.imageNotification.setImageResource(R.drawable.orange_add_shopping_cart_24);

                break;
            default:
                holder.imageNotification.setImageResource(R.drawable.red_add_shopping_cart_24);
        }

        holder.dateNotification.setText(date);

        holder.nextNotification.setText(formattedNextDate);

        holder.daysRemainig.setText(String.valueOf(daysRemaining) );



    }

    @Override
    public int getItemCount() {
        return updateNotificationModelsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dateNotification;
        private TextView daysRemainig;
        private TextView nextNotification;
        private ImageView imageNotification;

        public ViewHolder(View v) {
            super(v);

            dateNotification = (TextView) v.findViewById(R.id.txtdateNotification);
            daysRemainig = (TextView) v.findViewById(R.id.daysRemaining);
            nextNotification = (TextView) v.findViewById(R.id.daysNextNotification);
            imageNotification=(ImageView)v.findViewById(R.id.imageNotification);


        }
    }

}
