package com.example.fruteria.db.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fruteria.db.room.model.Fruta;

import java.util.List;

@Dao
public interface FrutaDao {

    @Insert
    void insertFruta(Fruta fruta);

    @Delete
    void deleteFruta(Fruta fruta);

    @Update
    void updateFruta(Fruta fruta);

    @Query("SELECT * FROM Fruta")
    List<Fruta> getFrutas();

    @Query("SELECT * FROM Fruta WHERE Fruta.idFruta= :idFruta")
    Fruta verFruta(int idFruta);

}