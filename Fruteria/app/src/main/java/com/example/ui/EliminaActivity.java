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

public class EliminaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimina);

        setTitle("Eliminar fruta");
    }

    public void borrarFruta(View view) {

        // Rescatar la id introcucida por el usuario
        EditText idFruta = findViewById(R.id.idEFruta);
        int id = Integer.parseInt(idFruta.getText().toString());

        if (id > 0) {
            FrutaBD conexion = FrutaBD.getConexion(this);
            FrutaDao frutaDao = conexion.frutaDao();
            Fruta fruta = frutaDao.verFruta(id);
            if (!fruta.getNombre().isEmpty()) {
                Toast.makeText(this, fruta.getNombre()+" borrada", Toast.LENGTH_SHORT).show();
                frutaDao.deleteFruta(fruta);
            } else {
                Toast.makeText(this, "No existe fruta con la id: " + id, Toast.LENGTH_SHORT).show();
            }
        }

        // Vuelta al main
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }
}