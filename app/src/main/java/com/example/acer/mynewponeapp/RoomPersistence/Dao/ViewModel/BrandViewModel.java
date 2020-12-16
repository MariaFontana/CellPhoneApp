package com.example.acer.mynewponeapp.RoomPersistence.Dao.ViewModel;

import android.app.Application;

import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.BrandRepository;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter.UsuarioRepositorio;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BrandViewModel extends AndroidViewModel
    {
        private BrandRepository brandRepository;
        private MutableLiveData<List<BrandModel>> brandMutableLiveData;
        private LiveData<List<BrandModel>> brandAll;
        private LiveData<BrandModel> BrandLive;
        private List<BrandModel> brandList;

            public BrandViewModel(@NonNull Application application) {
                super(application);
                brandRepository = new BrandRepository(application);
                brandAll = brandRepository.getAllBrand();
            }

      public  LiveData<List<BrandModel>> getBrandModel() {
            if (brandMutableLiveData == null) {
                brandMutableLiveData = new MutableLiveData<>();
            }

            return brandMutableLiveData;
        }


        public  LiveData<List<BrandModel>> getAllBrand() {
            return brandAll;
        }

        public  List<BrandModel> getAllBrandList() {
            return brandList;
        }

       public  void insert(BrandModel brand) {
            brandRepository.insert(brand);
        }





    }