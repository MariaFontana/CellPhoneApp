package com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel;

 import android.app.Application;
 import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.UpdateNotifictionRepository;
 import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.UpdateNotificationEntity;

 import java.util.List;

 import androidx.annotation.NonNull;
 import androidx.lifecycle.AndroidViewModel;
 import androidx.lifecycle.LiveData;
 import androidx.lifecycle.MutableLiveData;

public class NotificationViewModel extends AndroidViewModel {

    private  UpdateNotifictionRepository notificationRepository;
    private LiveData<List<UpdateNotificationEntity>> listNotification;
    private LiveData<List<UpdateNotificationEntity>> notificationAll;

    public NotificationViewModel(@NonNull Application application) {
        super(application);
        notificationRepository = new UpdateNotifictionRepository(application);
        notificationAll = notificationRepository.getAllNotification();
    }
}
