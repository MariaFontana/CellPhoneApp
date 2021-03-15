package com.example.acer.mynewponeapp.Bussines;

import com.example.acer.mynewponeapp.Bussines.Interfaces.IproductBussnes;
import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;
import com.example.acer.mynewponeapp.Model.UserModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductBussnes implements IproductBussnes {

    private ProductModel productModel;
    private List<ProductModel> listProductModel;
    private UpdateNotificationModel updateNotificationModel;

    public ProductBussnes() {

    }

    public ProductBussnes(UserModel userModel)
    {
        this.productModel = userModel.getProduct();
        if(userModel.getListNotificationModelList()!= null) {
            this.updateNotificationModel = userModel.getListNotificationModelList().get(0);
        }
    }
    public ProductBussnes( UpdateNotificationModel updateNotificationModel)
    {

        this.updateNotificationModel=updateNotificationModel;
    }


    public long CalculationDurationFeed()
    {

        if(updateNotificationModel != null) {

            Date dateStart = updateNotificationModel.getDateUpdate();

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
