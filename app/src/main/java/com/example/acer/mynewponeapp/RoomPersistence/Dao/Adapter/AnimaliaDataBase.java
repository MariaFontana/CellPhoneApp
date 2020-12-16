package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;


import android.content.Context;

import com.example.acer.mynewponeapp.Model.BrandModel;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.BrandDao;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.user;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Brand;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {BrandModel.class}, version = 1, exportSchema = false)

    abstract public class AnimaliaDataBase extends RoomDatabase {

       // abstract UserDao UserDao();
        abstract BrandDao BrandDao();

        // marking the instance as volatile to ensure atomic access to the variable
        private static volatile AnimaliaDataBase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static AnimaliaDataBase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (AnimaliaDataBase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AnimaliaDataBase.class, "Animaliaandroid")
                                .addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }



    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           databaseWriteExecutor.execute(() -> {
              //  Populate the database in the background.
    
                //If you want to start with more words, just add them.
              BrandDao brandDao = INSTANCE.BrandDao();
                brandDao.deleteAll();

                BrandModel brand = new BrandModel (0,"choose");
                brandDao.insert(brand);


            });
        }
    };

   // private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
      //  @Override
      //  public void onOpen(@NonNull SupportSQLiteDatabase db) {
      //      super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
       //     databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
         //       UsuarioDao dao = INSTANCE.UsuarioDao();
           //     dao.deleteAll();

            //    Usuario usuario = new Usuario();
            //    dao.insert(usuario);
           //     usuario = new Usuario();
            //    dao.insert(usuario);
          //  });
       // }
    //};
}
