package com.example.acer.mynewponeapp.Factory;

import com.example.acer.mynewponeapp.Bussines.ProductBussines;
import com.example.acer.mynewponeapp.Model.UpdateNotificationModel;

public class ProductFactory {

    public ProductFactory()
    {

    }
    public ProductBussines ProductBussiness(UpdateNotificationModel updateNotificationModel)
    {
        return new ProductBussines(updateNotificationModel);
    }
}
