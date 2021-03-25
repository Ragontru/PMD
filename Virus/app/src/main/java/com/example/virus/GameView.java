package com.example.virus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.virus.sprites.Sprite;

import java.util.LinkedList;

public abstract class GameView extends SurfaceView implements Runnable {

    Thread hilo = null;
    SurfaceHolder mSurfaceHolder;

    protected  volatile  boolean enEjecucion;
    public boolean pausado = true;

    Canvas canvas;
    Paint paint;
    long FPS;
    int mScreenX, mScreenY;

    private long ultimoProceso=0;
    private static int PERIODO_PROCESO=30;
    public float factor_mov;

    LinkedList<Sprite> actores=new LinkedList<>();
    long ahora, tiempo_transcurrido;


    public GameView(Context context, int x, int y) {
        super(context);
        mScreenX = x;
        mScreenY = y;
        mSurfaceHolder = this.getHolder();
        paint = new Paint();
    }

    @Override
    public void run() {
        while (enEjecucion) {
            if (!pausado) {
                update();
            }
        }
    }

    public void update(){
        ahora=System.currentTimeMillis();
        if(ultimoProceso+PERIODO_PROCESO>ahora){
            return;
        }
        tiempo_transcurrido=ahora-ultimoProceso;
        factor_mov=(tiempo_transcurrido)/PERIODO_PROCESO;
        ultimoProceso=ahora;

        actualiza();
        limpia();
        draw();
    }

    public abstract void actualiza();


    public void draw(){
        if (mSurfaceHolder.getSurface().isValid()) {
            canvas = mSurfaceHolder.lockCanvas();
            dibuja(canvas);

            paint.setTextSize(50);
            canvas.drawText("ahora: " + this.ahora, 50, 150, paint);
            canvas.drawText("ultimo proceso: " + this.ultimoProceso, 50, 250, paint);
            canvas.drawText("tiempo transcurrido: " + this.tiempo_transcurrido, 50, 350, paint);
            canvas.drawText("factor movimiento: " + this.factor_mov, 50, 450, paint);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public abstract void dibuja(Canvas canvas);

    synchronized public void limpia(){
        for (int i=0;i<actores.size();i++)
            if(!actores.get(i).isVisible()) {
                actores.remove(i);
            }
    }

    public void resume() {
        enEjecucion = true;
        hilo = new Thread(this);
        hilo.start();
    }

    public void pause() {
        enEjecucion = false;
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
