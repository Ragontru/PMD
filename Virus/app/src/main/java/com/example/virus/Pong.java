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

    public LinkedList<Sprite> getCelulas() {
        return celulas;
    }
    public LinkedList<Sprite> getViruses() {
        return viruses;
    }

    public LinkedList<Sprite> nuevosC=new LinkedList<>();
    public LinkedList<Sprite> nuevosV=new LinkedList<>();

    public Pong (Context context, int x, int y) {

        super(context,x,y);

        virus = new Virus(mScreenX, mScreenY);
        celula = new Celula(mScreenX, mScreenY);
        viruses.add(virus);
        celulas.add(celula);

        setupGame();

    }

    public void setupGame() {
        celula.reset();
        virus.reset();
    }

    @Override
    public void actualiza() {
        for (Sprite virus : viruses) {
            if(virus.isVisible())
                virus.update(this, FPS);
        }
        viruses.addAll(nuevosV);
        nuevosV=new LinkedList<>();

        for (Sprite celula : celulas) {
            if(celula.isVisible())
                celula.update(this, FPS);
        }
        celulas.addAll(nuevosC);
        nuevosC=new LinkedList<>();
    }


    @Override
    public void dibuja(Canvas canvas) {

        // Color fondo
        canvas.drawColor(Color.argb(255, 0, 0, 0));

        synchronized(celulas) {
            for (Sprite celula : celulas) {
                celula.pinta(canvas);
            }
        }

        synchronized(viruses) {
            for (Sprite virus : viruses) {
                virus.pinta(canvas);
            }
        }

        paint.setTextSize(50);
        paint.setColor(Color.rgb(255,255,51));
        canvas.drawText("  Células: " + celulas.size(), 10, 50, paint);
        canvas.drawText("  Virus: " + viruses.size(), 10, 150, paint);
    }

    // Recoge eventos de cuando se pulsa la pantalla
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                pausado = false;
                if (event.getX() < mScreenX / 2) {
                    celula = new Celula(mScreenX, mScreenY);
                    synchronized(celulas){
                        nuevosC.add(celula);
                    }

                } else {
                    virus = new Virus(2300, mScreenY);
                    synchronized (viruses){
                        nuevosV.add(virus);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //virus.setEstadoVirus(virus.STOP);
                break;
        }
        return true;
    }

}
