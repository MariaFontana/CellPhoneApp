package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.BrandDao;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.lifecycle.LiveData;

public class BrandRepository {

    private BrandDao brandDao;

    private LiveData<List<Brand>> brands;

    private LiveData<Brand> BrandLiveData;

    private  List<Brand> brandList;

    AnimaliaDataBase db;


    public BrandRepository(Application application) {

        db = AnimaliaDataBase.getDatabase(application);
        brandDao = db.BrandDao();
        brands = brandDao.getBrandAll();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Brand>> getAllBrand() {
        return   brands = db.BrandDao().getBrandAll();
    }

    public List<Brand> getAllBrandList() {
        return  brandDao.getBrandAllList();
    }

    public void insert(Brand brand) {
        AnimaliaDataBase.databaseWriteExecutor.execute(() -> {
            brandDao.insert(brand);
        });
    }
}
