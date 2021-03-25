package com.example.virus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.example.virus.sprites.Celula;
import com.example.virus.sprites.Virus;
import com.example.virus.sprites.Sprite;

import java.util.LinkedList;

public class Pong extends GameView {

    Celula celula;
    Virus virus;

    public LinkedList<Sprite> getActores() {
        return actores;
    }
    public LinkedList<Sprite> nuevos=new LinkedList<>();

    public Pong (Context context, int x, int y) {

        super(context,x,y);

        virus = new Virus(mScreenX, mScreenY);
        celula = new Celula(mScreenX, mScreenY);
        actores.add(virus);
        actores.add(celula);

        setupGame();

    }

    public void setupGame() {
        celula.reset();
        virus.reset();
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

        // Color fondo
        canvas.drawColor(Color.argb(255, 0, 0, 0));

        synchronized(actores) {
            for (Sprite actor : actores) {

                actor.pinta(canvas);


            }
        }

        paint.setTextSize(50);
        paint.setColor(Color.rgb(255,255,51));
        canvas.drawText("Factor_mov: " + this.factor_mov + "  Células: " + actores.size(), 10, 50, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                pausado = false;
                if (event.getX() < mScreenX / 2) {
                    // virus.setEstadoVirus(virus.LEFT);
                    celula = new Celula(mScreenX, mScreenY);
                    synchronized(actores){
                        nuevos.add(celula);
                    }

                } else {
                    //virus.setEstadoVirus(virus.RIGHT);
                }
                break;
            case MotionEvent.ACTION_UP:
                //virus.setEstadoVirus(virus.STOP);
                break;
        }
        return true;
    }

}
