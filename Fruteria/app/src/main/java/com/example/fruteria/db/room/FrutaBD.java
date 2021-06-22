package com.example.fruteria.db.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fruteria.db.room.dao.FrutaDao;
import com.example.fruteria.db.room.model.Fruta;

@Database(
        entities = {Fruta.class},
        version = 2
)

public abstract class FrutaBD extends RoomDatabase {

    public abstract FrutaDao frutaDao();

    private static volatile FrutaBD INSTANCE;

    public static FrutaBD getConexion(final Context context) {
        if (INSTANCE == null) {
            synchronized (FrutaBD.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FrutaBD.class,
                            "frutadb").allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;

    }

}