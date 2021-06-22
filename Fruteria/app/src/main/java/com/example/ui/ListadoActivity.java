package com.example.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.R;
import com.example.fruteria.db.room.model.Fruta;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    List<String> items = new ArrayList<>();
    ArrayList<Fruta> frutasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        setTitle("Listado");

        config();
    }

    private void config() {
        // Lista de frutas conectada
        lista = (ListView) findViewById(R.id.listViewFruta);

        // Carga de frutas
        Bundle extras = this.getIntent().getExtras();
        frutasList = (ArrayList<Fruta>) extras.getSerializable("frutas");

        for (Fruta frt : frutasList) {
            items.add(frt.getNombre() + " - " + frt.getVariedad());
        }

        // Adaptador para pasar las frutas
        MiArrayAdapter adaptadorFrutas = new MiArrayAdapter(
                this,
                R.layout.fruta_item,
                frutasList
        );

        lista.setAdapter(adaptadorFrutas);

        // Evento click sobre cada fruta
        lista.setOnItemClickListener(this);
    }

    // Método que es lanzado cada vez que se hace click en una fruta
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Obtener fruta gracias a la posición
        Fruta fruta = frutasList.get(position);
        Toast.makeText(this, "Fruta seleccionada: " + fruta.getNombre(), Toast.LENGTH_SHORT).show();

        // Cargar intent con los datos
        Intent intentMod = new Intent(this, ModificaActivity.class);

        // Ir al activity Modificar
        intentMod.putExtra("fruta", fruta);
        startActivity(intentMod);

    }
}