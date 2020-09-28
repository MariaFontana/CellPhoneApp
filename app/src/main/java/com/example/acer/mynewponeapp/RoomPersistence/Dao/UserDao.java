package com.example.acer.mynewponeapp.RoomPersistence.Dao;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("SELECT * from user ORDER BY name  ASC")
    LiveData<List<user>> getUserAll();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(user user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user WHERE idUser IN (:userIds)")
    LiveData<user> GetUserById(int userIds);

    @Query("SELECT * FROM user WHERE mail like :mail and password like :password")
    user GetUserLogin(String  mail, String password);



}
