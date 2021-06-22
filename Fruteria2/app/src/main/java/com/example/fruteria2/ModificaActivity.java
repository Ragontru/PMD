package com.example.fruteria2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ModificaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);
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
                Toast.makeText(this,"Has elegido: Listado de frutas",Toast.LENGTH_SHORT).show();
                initListadoAct();
                return true;
            case R.id.enlAddFruta:
                Toast.makeText(this,"Has elegido: Añadir fruta",Toast.LENGTH_SHORT).show();
                initNuevaAct();
                return true;
            case R.id.enlUpdFruta:
                Toast.makeText(this,"Has elegido: Modificar fruta",Toast.LENGTH_SHORT).show();
                initModificaAct();
                return true;
            case R.id.enlEliFruta:
                Toast.makeText(this,"Has elegido: Eliminar fruta",Toast.LENGTH_SHORT).show();
                initEliminaAct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Ir al activity Listado
    public void initListadoAct() {
        Intent intentListado = new Intent(this, ListadoActivity.class);
        startActivity(intentListado);
    }

    // Ir al activity Nueva
    public void initNuevaAct() {
        Intent intentNueva = new Intent(this, NuevaActivity.class);
        startActivity(intentNueva);
    }

    // Ir al activity Modifica
    public void initModificaAct() {
        Intent intentModifica = new Intent(this, ModificaActivity.class);
        startActivity(intentModifica);
    }

    // Ir al activity Elimina
    public void initEliminaAct() {
        Intent intentElimina = new Intent(this, EliminaActivity.class);
        startActivity(intentElimina);
    }
}