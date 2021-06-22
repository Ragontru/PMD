package com.example.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.db.R;
import com.example.fruteria.db.room.FrutaBD;
import com.example.fruteria.db.room.dao.FrutaDao;
import com.example.fruteria.db.room.model.Fruta;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Frutería");
    }

    // Para el menú desplegable
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    // Para las opciones del menú desplegable
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enlListFruta:
                initListadoAct();
                return true;
            case R.id.enlAddFruta:
                initNuevaAct();
                return true;
            case R.id.enlEliFruta:
                initEliminaAct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Ir al activity Listado
    public void initListadoAct() {


        FrutaBD conexion = FrutaBD.getConexion(this);
        FrutaDao frutaDao = conexion.frutaDao();
        ArrayList<Fruta> frutas = (ArrayList<Fruta>) frutaDao.getFrutas();
        Toast.makeText(this,"Carga de intent", Toast.LENGTH_SHORT).show();

        Intent intentListado = new Intent(this, ListadoActivity.class);
        intentListado.putExtra("frutas", frutas);
        Toast.makeText(this,"Listado de frutas", Toast.LENGTH_SHORT).show();
        startActivity(intentListado);
    }

    // Ir al activity Nueva
    public void initNuevaAct() {
        Intent intentNueva = new Intent(this, NuevaActivity.class);
        startActivity(intentNueva);
    }

    // Ir al activity Elimina
    public void initEliminaAct() {
        Intent intentElimina = new Intent(this, EliminaActivity.class);
        startActivity(intentElimina);
    }

}