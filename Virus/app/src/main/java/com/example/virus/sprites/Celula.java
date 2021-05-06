package com.example.virus.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.virus.GameView;
import com.example.virus.Pong;

import java.util.LinkedList;
import java.util.Random;

public class Celula extends Sprite {

    public Celula(int x, int y) {
        super(x,y);
        ancho=mScreenX/50;
        alto=ancho;

        // Rectangulo que delinea
        mRect=new RectF(mXCoord, mYCoord, mXCoord+ ancho, mYCoord+ alto);


        velInicialX=mScreenY/100;
        velInicialY=mScreenY/100;
        velActualX=velInicialX;
        velActualY=velInicialY;
    }

    public void invertirVelX(){
        velActualX=-velActualX;
    }

    public void invertirVelY(){
        velActualY=-velActualY;
    }

    // Acelera aleatoriamete la velocidad
    public void setRandomXVelocity(){
        Random random=new Random();
        int addVelocity=random.nextInt(2);
        this.velActualX+=addVelocity;
        if (addVelocity==0)invertirVelX();
    }

    @Override
    public void reset() {
        mRect.left=mScreenX/2;
        mRect.right=mScreenX/2+ ancho;
        mRect.top=mScreenY-20;
        mRect.bottom=mScreenY-20+alto;
        this.velActualX=velInicialX;
        this.velActualY=velInicialY;
    }

    @Override
    public void update(GameView game, float fps) {
        Pong pong=(Pong)game;

        mRect.left=mRect.left+velActualX*pong.factor_mov;
        mRect.top=mRect.top+velActualY*pong.factor_mov;

        mRect.right=mRect.left+ancho;
        mRect.bottom=mRect.top+alto;

        // Comprobacion de colisiones
        LinkedList<Sprite> objetos=pong.getCelulas();
        for(Sprite objeto:objetos){
            if (!objeto.equals(this)){
                if (objeto.isVisible()&&colision(objeto)){
                    if (objeto instanceof Celula) {
                        ((Celula) objeto).setRandomXVelocity();
                        ((Celula) objeto).invertirVelY();
                        objeto.recolocaY(objeto.getRect().top - 2);
                        setRandomXVelocity();
                        invertirVelY();
                    }else{
                        setRandomXVelocity();
                        invertirVelY();
                        recolocaY(objeto.getRect().top - 2);
                    }
                }
            }
        }

        LinkedList<Sprite> objetos2=pong.getViruses();
        for(Sprite objeto:objetos2){
            if (!objeto.equals(this)){
                if (objeto.isVisible()&&colision(objeto)){
                    if (objeto instanceof Celula) {
                        ((Celula) objeto).setRandomXVelocity();
                        ((Celula) objeto).invertirVelY();
                        objeto.recolocaY(objeto.getRect().top - 2);
                        setRandomXVelocity();
                        invertirVelY();
                    }else{
                        setRandomXVelocity();
                        invertirVelY();
                        recolocaY(objeto.getRect().top - 2);
                    }
                }
            }
        }

        // Celula sale por la derecha
        if (colisionBordeRight()) {
            invertirVelX();
            recolocaX(mScreenX - ancho - 20);

        }

        // Celula sale por izquierda
        if (colisionBordeLeft()) {
            invertirVelX();
            recolocaX(2);
        }

        // Celula sale por arriba
        if (colisionBordeTop()) {
            invertirVelY();
            recolocaY(50);
        }

        // Celula sale por abajo
        if (colisionBordeBottom()) {
            invertirVelY();
            recolocaY(mScreenY - 2);

        }
    }

    public  void pinta(Canvas canvas){
        paint.setColor(Color.rgb(255,26,26));
        paint.setStyle(Paint.Style.STROKE);
        float centroX=ancho/2+ mRect.left;
        float centroY=alto/2+mRect.top;
        canvas.drawCircle(centroX,centroY,ancho, paint);
    }
}
