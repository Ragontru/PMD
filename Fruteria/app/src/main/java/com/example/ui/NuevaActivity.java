package com.example.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.R;
import com.example.fruteria.db.room.FrutaBD;
import com.example.fruteria.db.room.dao.FrutaDao;
import com.example.fruteria.db.room.model.Fruta;

public class NuevaActivity extends AppCompatActivity {

    Fruta fruta;
    EditText nombreEditText;
    EditText varieEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva);

        setTitle("Nueva fruta");
        config();
    }

    public void config() {
        // Rescatar datos introducidos por el usuario
        nombreEditText = findViewById(R.id.nombreNFruta);
        varieEditText = findViewById(R.id.varieNFruta);
    }

    public void guardarFruta(View view) {
        fruta = new Fruta();
        String nombre = nombreEditText.getText().toString();
        String variedad = varieEditText.getText().toString();
        fruta.setNombre(nombre);
        fruta.setVariedad(variedad);
        FrutaBD conexion = FrutaBD.getConexion(this);
        FrutaDao frutaDao = conexion.frutaDao();
        frutaDao.insertFruta(fruta);
        Toast.makeText(this, "NOMBRE:" + fruta.getNombre() + " - VARIEDAD:" + fruta.getVariedad() + " añadida", Toast.LENGTH_SHORT).show();

        // Vuelta al main
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }

}