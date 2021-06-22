package com.example.fruteria.db.room.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Fruta implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idFruta;

    private String nombre;

    private String variedad;

    // Constructor vacío
    public Fruta() {
    }

    // Constructor con nombre
    public Fruta(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public Fruta(int idFruta, String nombre, String variedad) {
        this.idFruta = idFruta;
        this.nombre = nombre;
        this.variedad = variedad;
    }

    //Getters y setters
    public int getIdFruta() {
        return idFruta;
    }

    public void setIdFruta(int idFruta) {
        this.idFruta = idFruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

}
