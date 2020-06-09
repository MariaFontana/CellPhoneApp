package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.UsuarioDao;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Usuario;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UsuarioRepositorio {
    private UsuarioDao mUsuarioDao;
    private LiveData<List<Usuario>> mAllUsuario;
    private LiveData<Usuario> usuarioLiveData;


    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    UsuarioRepositorio(Application application) {
        UsuarioDataBase db = UsuarioDataBase.getDatabase(application);
        mUsuarioDao = db.UsuarioDao();
        mAllUsuario = mUsuarioDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Usuario>> getAllUsuario() {
        return mAllUsuario;
    }


    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Usuario usuario) {
        try {
            UsuarioDataBase.databaseWriteExecutor.execute(() -> {

                mUsuarioDao.insert(usuario);
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }

    void GetUserById(int id) {
        try {
            UsuarioDataBase.databaseWriteExecutor.execute(() -> {

                mUsuarioDao.GetUserById(id);
            });
        } catch (Exception e) {
            e.getMessage();
        }

    }

    void  GetUserByLogin(String mail, String password) {

        try {
            UsuarioDataBase.databaseWriteExecutor.execute(() -> {

                 mUsuarioDao.GetUserLogin(mail, password);
            });
        } catch (Exception e) {
            e.getMessage();
        }



    }
}
