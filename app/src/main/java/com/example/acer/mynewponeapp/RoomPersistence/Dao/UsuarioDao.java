package com.example.acer.mynewponeapp.RoomPersistence.Dao;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Usuario;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UsuarioDao {
    @Query("SELECT * from usuario ORDER BY nombre  ASC")
    LiveData<List<Usuario>> getAlphabetizedWords();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Usuario usuario);

    @Query("DELETE FROM usuario")
    void deleteAll();

    @Query("SELECT * FROM usuario WHERE id IN (:userIds)")
    LiveData<Usuario> GetUserById(int userIds);

    @Query("SELECT * FROM usuario WHERE mail like :mail and password like :password")
    Usuario GetUserLogin(String  mail, String password);



}
