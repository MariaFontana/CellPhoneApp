package com.example.acer.mynewponeapp.Model;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateNotificationModel {

    private BigInteger idUpdatenotification;

    public UpdateNotificationModel(BigInteger idUpdatenotification, Integer countDays, UserModel userModel) {
        this.idUpdatenotification = idUpdatenotification;
        this.dateUpdate = dateUpdate;
        this.countDays = countDays;
        this.userModel = userModel;
    }




    public UpdateNotificationModel(BigInteger idUpdatenotification, String dateUpdate, Integer countDays)  {

        this.idUpdatenotification = idUpdatenotification;
        this.dateUpdate=  GetDate(dateUpdate);
        this.countDays = countDays;

    }



    private Date dateUpdate;

    public void setIdUpdatenotification(BigInteger idUpdatenotification) {
        this.idUpdatenotification = idUpdatenotification;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setCountDays(Integer countDays) {
        this.countDays = countDays;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    private Integer countDays;

    public BigInteger getIdUpdatenotification() {
        return idUpdatenotification;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public Integer getCountDays() {
        return countDays;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    private UserModel userModel;


    private Date GetDate(String dateUpdate )
    {
        try {
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
            this.dateUpdate  =(Date)dateParser.parse(dateUpdate);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return this.dateUpdate;
    }

}
