package com.example.acer.mynewponeapp.RoomPersistence.Dao;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.UpdateNotificationEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface  UpdateNotificationDao {

@Insert
public void addData(UpdateNotificationEntity mydatalist);

@Query("select * from updatenotification")
public LiveData<List<UpdateNotificationEntity>> getUpdateNotificationData();


@Delete
public void delete(UpdateNotificationEntity mydatalist);

@Update
public void update(UpdateNotificationEntity mydatalist);



        }
