package com.example.virus.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.virus.GameView;
import com.example.virus.Pong;

import java.util.LinkedList;
import java.util.Random;

public class Virus extends  Sprite{

    public Virus(int x, int y) {
        super(x,y);
        radio=mScreenX/100;
        centroX=mScreenX/2;
        centroY=mScreenY/2;

        velInicialX=mScreenY/50;
        velInicialY=mScreenY/50;
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

        // Virus sale por la derecha
        if (colisionBordeRight()) {
            invertirVelX();
            recolocaX(mScreenX - ancho - 2);

        }

        // Virus sale por izquierda
        if (colisionBordeLeft()) {
            invertirVelX();
            recolocaX(2);
        }

        // Virus sale por arriba
        if (colisionBordeTop()) {
            invertirVelY();
            recolocaY(10);
        }

        // Virus sale por abajo
        if (colisionBordeBottom()) {
            invertirVelY();
            recolocaY(mScreenY - 2);

        }
    }

    public  void pinta(Canvas canvas){
        paint.setColor(Color.rgb(51,204,0));
        float centroX=ancho/2+ mRect.right;
        float centroY=alto/2+mRect.bottom;
        canvas.drawCircle(centroX,centroY,radio/2, paint);
    }
}
