package com.example.virus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.virus.sprites.Bola;
import com.example.virus.sprites.Pala;
import com.example.virus.sprites.Sprite;

import java.util.LinkedList;

public class Pong extends GameView {

    Bola bola;
    Pala pala;

    public LinkedList<Sprite> getActores() {
        return actores;
    }
    public LinkedList<Sprite> nuevos=new LinkedList<>();

    public int puntuacion = 0;
    public int vidas = 3;


    public Pong (Context context, int x, int y) {

        super(context,x,y);

        pala = new Pala(mScreenX, mScreenY);
        bola = new Bola(mScreenX, mScreenY);
        actores.add(pala);
        actores.add(bola);

        setupGame();

    }

    public void setupGame() {
        bola.reset();
        pala.reset();
        if (vidas == 0) {
            puntuacion = 0;
            vidas = 3;

        }

    }

    @Override
    public void actualiza() {

        for (Sprite actor : actores) {
            if(actor.isVisible())
                actor.update(this, FPS);
        }
        actores.addAll(nuevos);
        nuevos=new LinkedList<>();
    }


    @Override
    public void dibuja(Canvas canvas) {

        canvas.drawColor(Color.argb(255, 20, 128, 188));

        synchronized(actores) {
            for (Sprite actor : actores) {

                actor.pinta(canvas);


            }
        }

        paint.setTextSize(30);
        canvas.drawText("Factor_mov: " + this.factor_mov + "  Vidas: " + actores.size(), 10, 50, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                pausado = false;
                if (event.getX() < mScreenX / 2) {
                    pala.setEstadoPala(pala.LEFT);
                    bola = new Bola(mScreenX, mScreenY);
                    synchronized(actores){
                        nuevos.add(bola);
                    }

                    puntuacion++;




                } else {
                    pala.setEstadoPala(pala.RIGHT);
                }
                break;
            case MotionEvent.ACTION_UP:
                pala.setEstadoPala(pala.STOP);
                break;

        }
        return true;
    }


}
