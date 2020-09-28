package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepositorio mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<user>> mAllWords;
    private LiveData<user> usuarioLive;

    public UsuarioViewModel(Application application) {
        super(application);
        mRepository = new UsuarioRepositorio(application);
       // mAllWords = mRepository.getAllUsuario();
    }

    LiveData<List<user>> getAllWords() {
        return mAllWords;
    }

    public void insert(user usuario) {
        mRepository.insert(usuario);
    }

    void getUserById(int id)
    {
        mRepository.GetUserById(id);
    }


}
