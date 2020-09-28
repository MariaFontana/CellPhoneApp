package com.example.acer.mynewponeapp.RoomPersistence.Dao;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface BrandDao {

    @Query("SELECT * from brand")
    LiveData<List<Brand>> getBrandAll();

    @Query("SELECT * from brand")
    List<Brand> getBrandAllList();


}
