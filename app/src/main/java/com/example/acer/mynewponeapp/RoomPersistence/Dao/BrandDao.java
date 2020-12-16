package com.example.acer.mynewponeapp.RoomPersistence.Dao;

import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BrandDao {

    @Query("SELECT * from brand")
    LiveData<List<BrandModel>> getBrandAll();

    @Query("SELECT * from brand")
    List<BrandModel> getBrandAllList();

    @Query("DELETE FROM brand")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BrandModel brand);

}
