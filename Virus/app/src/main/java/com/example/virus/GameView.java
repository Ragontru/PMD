package com.example.virus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.virus.sprites.Sprite;

import java.util.LinkedList;

// SurfaceView: Clase de grafico que refresca la pantalla mas rapido a View
// Usa hilo para la ejecucion del juego
public abstract class GameView extends SurfaceView implements Runnable {

    Thread hilo = null;
    // Superficie general para dibujar en ella
    SurfaceHolder mSurfaceHolder;

    // Variable que se debe conocer dentro y fuera del hilo (volatile)
    // Visible desde dentro del hilo interno y ver variables fuera del hilo
    protected  volatile  boolean enEjecucion;
    public boolean pausado = true;

    // PANTALLA Y SOFTWARE
    Canvas canvas;
    Paint paint;
    long FPS;
    int mScreenX, mScreenY;

    private long ultimoProceso=0;
    private static int PERIODO_PROCESO=30;
    public float factor_mov;

    // Sprite = cosas que aparecen en pantalla (escenario y jugadores, tienen propiedades)
    //LinkedList<Sprite> actores=new LinkedList<>();
    LinkedList<Sprite> celulas=new LinkedList<>();
    LinkedList<Sprite> viruses=new LinkedList<>();
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
            // canvas.drawText("tiempo transcurrido: " + this.tiempo_transcurrido, 50, 350, paint);
            // canvas.drawText("factor movimiento: " + this.factor_mov, 50, 450, paint);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public abstract void dibuja(Canvas canvas);

    synchronized public void limpia(){
        for (int i=0;i<celulas.size();i++)
            if(!celulas.get(i).isVisible()) {
                celulas.remove(i);
            }

        for (int i=0;i<viruses.size();i++)
            if(!viruses.get(i).isVisible()) {
                viruses.remove(i);
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
