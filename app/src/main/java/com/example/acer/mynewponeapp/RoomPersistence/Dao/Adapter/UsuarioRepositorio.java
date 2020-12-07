package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.UserDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UsuarioRepositorio {

    private UserDao userDao;
    private LiveData<List<user>> users;
    private LiveData<user> usuarioLiveData;


    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    UsuarioRepositorio(Application application) {
        AnimaliaDataBase db = AnimaliaDataBase.getDatabase(application);
       // userDao = db.UserDao();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<user>> getAllUser() {
        return   users = userDao.getUserAll();
    }


    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(user usuario) {
        try {
            AnimaliaDataBase.databaseWriteExecutor.execute(() -> {

                userDao.insert(usuario);
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }

    void GetUserById(int id) {
        try {
            AnimaliaDataBase.databaseWriteExecutor.execute(() -> {

                userDao.GetUserById(id);
            });
        } catch (Exception e) {
            e.getMessage();
        }

    }

    void  GetUserByLogin(String mail, String password) {

        try {
            AnimaliaDataBase.databaseWriteExecutor.execute(() -> {

                 userDao.GetUserLogin(mail, password);
            });
        } catch (Exception e) {
            e.getMessage();
        }



    }
}
