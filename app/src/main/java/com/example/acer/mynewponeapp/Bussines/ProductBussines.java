package com.example.acer.mynewponeapp.Bussines;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IproductBussines;
import com.example.acer.mynewponeapp.DataBase.ProductByCategoryAsync;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductBussines implements IproductBussines {

    private ProductModel productModel;
    private List<ProductModel> listProductModel;
    private UpdateNotificationModel updateNotificationModel;
    private RecyclerView recycleViewAccesorio;
    private Context context;
    public String formattedDate;

    public ProductBussines( RecyclerView recycleViewAccesorio, Context context) {

        this.recycleViewAccesorio= recycleViewAccesorio;
        this.context=context;

    }

    public ProductBussines(UserModel userModel, RecyclerView recycleViewAccesorio, Context context)
    {
        this.productModel = userModel.getProduct();
        if(userModel.getListNotificationModelList()!= null) {
            this.updateNotificationModel = userModel.getListNotificationModelList().get(0);
        }

        this.context=context;
    }

    public ProductBussines(UserModel userModel, Context context)
    {
        this.productModel = userModel.getProduct();
        if(userModel.getListNotificationModelList()!= null) {
            this.updateNotificationModel = userModel.getListNotificationModelList().get(0);
        }

        this.context=context;
    }


    public ProductBussines(UpdateNotificationModel updateNotificationModel)
    {

        this.updateNotificationModel=updateNotificationModel;
    }

    public List<ProductModel> GetAccesorios() {

        ProductByCategoryAsync ListAccesorio= new ProductByCategoryAsync(context,recycleViewAccesorio);
        ListAccesorio.execute();

        return null;
    }

    public long CalculationDurationFeed()
    {

        if(updateNotificationModel != null) {

            Date dateStart = updateNotificationModel.getDateUpdate();
            formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(dateStart);

            int days = updateNotificationModel.getCountDays();
            Date dateNow =Calendar.getInstance().getTime();
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateStart);
            calendarNow.add(Calendar.DAY_OF_YEAR, days);
            Date dateFinish =calendarNow.getTime();
            if(dateFinish.getTime() < dateNow.getTime())
            {
                return 0 ;
            }
            else {
                long difference_In_Time = dateFinish.getTime() - dateStart.getTime();

                long difference_In_Days = (difference_In_Time
                        / (1000 * 60 * 60 * 24))
                        % 365;

                return difference_In_Days;
            }
        }
        return 0;
    }



}
