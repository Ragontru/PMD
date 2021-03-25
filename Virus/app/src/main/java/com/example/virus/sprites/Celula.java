package com.example.virus.sprites;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.virus.GameView;
import com.example.virus.Pong;

import java.util.LinkedList;
import java.util.Random;

public class Celula extends Sprite {
    public Celula(int x, int y) {
        super(x,y);
        ancho=mScreenX/100;
        alto=ancho;

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


    public void setRandomXVelocity(){
        Random random=new Random();
        int addVelocity=random.nextInt(2);
        this.velActualX+=addVelocity;
        if (addVelocity==0)invertirVelX();
    }

    public void icrementaVelocidad(){
        velActualX*=1.1f;
        velActualY*=1.1f;
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

        LinkedList<Sprite> objetos=pong.getActores();
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

        if (colisionBordeRight()) {
            invertirVelX();
            recolocaX(mScreenX - ancho - 2);

        }
        if (colisionBordeLeft()) {
            invertirVelX();
            recolocaX(2);
        }

        if (colisionBordeTop()) {
            invertirVelY();
            recolocaY(10);
        }

        if (colisionBordeBottom()) {
            invertirVelY();
            recolocaY(mScreenY - 2);
            pong.vidas--;
            if ( pong.vidas == 0) {

            }
        }
    }

    public  void pinta(Canvas canvas){
        paint.setColor(color);
        float centroX=ancho/2+ mRect.left;
        float centroY=alto/2+mRect.top;
        canvas.drawCircle(centroX,centroY,ancho/2, paint);
    }
}
