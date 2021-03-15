package com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades;

import com.example.acer.mynewponeapp.Model.UserModel;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

@Entity(tableName = "updatenotification")
public class UpdateNotificationEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer idUpdatenotification;



    @ColumnInfo(name = "dateUpdate")
    private long mdateUpdate;

    @ColumnInfo(name = "countDay")
    private Integer countDay;

    @Ignore
    public UpdateNotificationEntity(Integer idUpdatenotification, long dateUpdate, Integer countDays)  {

        this.idUpdatenotification = idUpdatenotification;
        this.mdateUpdate=  dateUpdate;
        this.countDay = countDays;

    }

    public UpdateNotificationEntity()  {


    }

    public long getMdateUpdate() {
        return mdateUpdate;
    }

    public void setMdateUpdate(long mdateUpdate) {
        this.mdateUpdate = mdateUpdate;
    }

    public Integer getCountDay() {
        return countDay;
    }


    public Integer getIdUpdatenotification() {
        return idUpdatenotification;
    }

    public void setIdUpdatenotification( Integer idUpdatenotification) {
        this.idUpdatenotification = idUpdatenotification;
    }

    public void setCountDay(Integer countDay) {
        this.countDay = countDay;
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}

