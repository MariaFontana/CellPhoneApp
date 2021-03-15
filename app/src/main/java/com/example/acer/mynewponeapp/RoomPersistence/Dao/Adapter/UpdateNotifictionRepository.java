package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.UpdateNotificationEntity;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.UpdateNotificationDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UpdateNotifictionRepository {

    private UpdateNotificationDao updateNotificationDao;
    private LiveData<List<UpdateNotificationEntity>> allNotifiction;

    public UpdateNotifictionRepository(Application application) {
        AnimaliaDataBase db = AnimaliaDataBase.getDatabase(application);
        this.updateNotificationDao = db.updateNotificationDao();
        this.allNotifiction = updateNotificationDao.getUpdateNotificationData();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
   public LiveData<List<UpdateNotificationEntity>> getAllNotification() {
        return allNotifiction;
    }
}
