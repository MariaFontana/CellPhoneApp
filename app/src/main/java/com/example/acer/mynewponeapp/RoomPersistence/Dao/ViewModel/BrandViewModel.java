package com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel;

import android.app.Application;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.BrandRepository;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.UsuarioRepositorio;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BrandViewModel extends AndroidViewModel
    {
        private BrandRepository brandRepository;
        private LiveData<List<Brand>> brandAll;
        private LiveData<Brand> BrandLive;
        private List<Brand> brandList;

            public BrandViewModel(@NonNull Application application) {
                super(application);
                brandRepository = new BrandRepository(application);
                brandAll = brandRepository.getAllBrand();
            }

      public  LiveData<List<Brand>> getAllBrand() {
            return brandAll;
        }

        public  List<Brand> getAllBrandList() {
            return brandList;
        }

       public  void insert(Brand brand) {
            brandRepository.insert(brand);
        }





    }