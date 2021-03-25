package com.example.videojuego;


import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

/*

Implementación del esquema general de un videojuego.
También lo convertiremos en un motor de videojuegos para futuros proyectos
En Android los juegos se programan directamente "a pelo"

*/

// Hilo principal
public class MainActivity extends Activity {

    Pong pong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtener la pantalla
            // Dibuja en pantalla (borrar y dibujar = animar)
        Display display= getWindowManager().getDefaultDisplay();
            // Estructura que contiene datos para darle un maño en la siguiente linea
        Point size=new Point();
        display.getSize(size);
        //gameView=new GameView(this,area.right-area.left,area.bottom-area.top);

        // Se le pasa a Pong (juego paticular). Aqui va a estar todo el juego
        pong=new Pong(this,size.x,size.y);

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
}