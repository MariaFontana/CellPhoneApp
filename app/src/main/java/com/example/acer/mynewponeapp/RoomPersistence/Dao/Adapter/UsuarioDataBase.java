package com.example.acer.mynewponeapp.RoomPersistence.Dao.Adapter;


import android.content.Context;

import com.example.acer.mynewponeapp.RoomPersistence.Dao.UsuarioDao;
import com.example.acer.mynewponeapp.RoomPersistence.Dao.Entidades.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)

    abstract public class UsuarioDataBase extends RoomDatabase {

        abstract UsuarioDao UsuarioDao();

        // marking the instance as volatile to ensure atomic access to the variable
        private static volatile UsuarioDataBase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static UsuarioDataBase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (UsuarioDataBase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                UsuarioDataBase.class, "Animalia_Android")
                                //.addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

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
