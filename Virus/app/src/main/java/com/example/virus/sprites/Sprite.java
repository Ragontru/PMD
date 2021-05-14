package com.example.virus.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.virus.GameView;

public abstract class Sprite {

    int mScreenX, mScreenY;

    float centroX = mScreenX/2;
    float centroY = mScreenY/2;

    public int getmScreenY() {
        return mScreenY;
    }

    float radio ;

    float velInicialX;
    float velInicialY;

    float velActualX;
    float velActualY;

    boolean visible;
    int color;

    Paint paint;

    public boolean isVisible() {
        return visible;
    }

    public Sprite(int screenX, int screenY){

        mScreenX=screenX;
        mScreenY=screenY;
        radio = 100;
        color=Color.argb(255, 255, 255, 255);
        visible=true;
        paint=new Paint();
    }

    public boolean colisionBordeLeft(){
        if (centroX-radio<0)return true;
        return false;
    }
    public boolean colisionBordeRight(){
        if (centroX+radio>mScreenX)return true;
        return false;
    }
    public boolean colisionBordeTop(){
        if (centroY-radio< 0)return true;
        return false;
    }
    public boolean colisionBordeBottom(){
        if (centroY+radio> mScreenY)return true;
        return false;
    }
    public void recolocaX(float x){
        centroX - radio=x;
        centroX + radio=x+radio;
    }
    public void recolocaY(float y){
        centroY+radio=y;
        centroY-radio=y-radio;
    }

    public abstract void reset();
    public abstract void update(GameView game, float fps);


}
