package com.example.acer.mynewponeapp.Model;

import java.math.BigInteger;
import java.util.Date;

public class UpdateNotificationModel {

    private BigInteger idUpdatenotification;

    public UpdateNotificationModel(BigInteger idUpdatenotification, Date dateUpdate, Integer countDays, UserModel userModel) {
        this.idUpdatenotification = idUpdatenotification;
        this.dateUpdate = dateUpdate;
        this.countDays = countDays;
        this.userModel = userModel;
    }

    public UpdateNotificationModel(BigInteger idUpdatenotification, Date dateUpdate, Integer countDays) {

        this.idUpdatenotification = idUpdatenotification;
        this.dateUpdate = dateUpdate;
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

}
