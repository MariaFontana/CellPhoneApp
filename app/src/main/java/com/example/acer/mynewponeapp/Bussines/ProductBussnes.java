package com.example.acer.mynewponeapp.Bussines;

import com.example.acer.mynewponeapp.Model.ProductModel;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductBussnes {

    private ProductModel productModel;
    private List<ProductModel> listProductModel;
    private UpdateNotificationModel updateNotificationModel;

    public ProductBussnes() {

    }

    public ProductBussnes(ProductModel productModel, UpdateNotificationModel updateNotificationModel)
    {
        this.productModel = productModel;
        this.updateNotificationModel=updateNotificationModel;
    }


    public int CalculationDurationFeed()
    {
        if(updateNotificationModel != null) {
            Date fecha = updateNotificationModel.getDateUpdate();
            int days = updateNotificationModel.getCountDays();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return calendar.DAY_OF_MONTH;
        }
        return 0;

    }


}
