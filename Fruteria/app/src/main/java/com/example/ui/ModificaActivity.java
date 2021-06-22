package com.example.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.R;
import com.example.fruteria.db.room.FrutaBD;
import com.example.fruteria.db.room.dao.FrutaDao;
import com.example.fruteria.db.room.model.Fruta;

public class ModificaActivity extends AppCompatActivity {

    Fruta fruta;
    EditText nombre;
    EditText variedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);
        setTitle("Editar fruta");

        Bundle bundle = this.getIntent().getExtras();
        fruta = (Fruta) bundle.getSerializable("fruta");
        config();
    }

    private void config() {
        nombre = findViewById(R.id.nombreMFruta);
        variedad = findViewById(R.id.varieMFruta);
        nombre.setText(fruta.getNombre());
        variedad.setText(fruta.getNombre());
    }

    public void initMainIntent() {

    }

    public void modificarFruta(View view) {
        fruta.setNombre(nombre.getText().toString());
        fruta.setVariedad(variedad.getText().toString());

        FrutaBD conexion = FrutaBD.getConexion(this);
        FrutaDao frutaDao = conexion.frutaDao();
        frutaDao.updateFruta(fruta);
        Toast.makeText(this, fruta.getNombre()+" actualizado", Toast.LENGTH_SHORT).show();
        initMainIntent();

    }
}