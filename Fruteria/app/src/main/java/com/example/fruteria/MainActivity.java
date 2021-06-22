package com.example.fruteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("TAG cicloVida", "Ciclovida: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("TAG cicloVida", "Ciclovida: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("TAG cicloVida", "Ciclovida: onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("TAG cicloVida", "Ciclovida: onStop");
    }

    public void initListadoAct(View view) {
        Toast.makeText(this, "Bienvenido/a", Toast.LENGTH_LONG).show();

        Intent intentLista = new Intent(this, ListaActivity.class);
        startActivity(intentLista);
    }
}