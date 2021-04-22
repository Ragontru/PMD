package com.example.virus;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

// Hilo principal
public class MainActivity extends Activity {

    Pong pong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener pantalla
            // Dibuja en pantalla (borrar y dibujar = animar)
        Display display = getWindowManager().getDefaultDisplay();
            // Estructura que contiene datos para darle un tamaño en la siguiente linea
        Point size = new Point();
        display.getSize(size);

        // Se le pasa a Pong (juego particular), aqui va a estar todo el juego
        pong = new Pong(this, size.x, size.y);

        // Muestra el juego en pantalla
        setContentView(pong);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pong.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pong.pause();
    }

    public void startGame(View view) {
    }
}