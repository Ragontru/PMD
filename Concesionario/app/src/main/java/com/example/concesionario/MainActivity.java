package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("TAG cicloVida","Ciclovida: onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("TAG cicloVida","Ciclovida: onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("TAG cicloVida","Ciclovida: onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("TAG cicloVida","Ciclovida: onStop");
    }
}