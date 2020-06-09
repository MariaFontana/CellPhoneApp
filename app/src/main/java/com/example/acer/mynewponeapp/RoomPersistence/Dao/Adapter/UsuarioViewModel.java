package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Usuario;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepositorio mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Usuario>> mAllWords;
    private LiveData<Usuario> usuarioLive;

    public UsuarioViewModel(Application application) {
        super(application);
        mRepository = new UsuarioRepositorio(application);
        mAllWords = mRepository.getAllUsuario();
    }

    LiveData<List<Usuario>> getAllWords() {
        return mAllWords;
    }

    public void insert(Usuario usuario) {
        mRepository.insert(usuario);
    }

    void getUserById(int id)
    {
        mRepository.GetUserById(id);
    }
  // public Usuario GetUserByLogin(String  mail, String password)
  //  {
    //   Usuario user =mRepository.GetUserByLogin(mail,password);
      // return user;
    //}


}
