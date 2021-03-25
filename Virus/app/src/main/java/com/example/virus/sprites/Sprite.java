package com.example.virus.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.virus.GameView;

import java.util.LinkedList;
import java.util.List;

public abstract class Sprite {

    int mScreenX, mScreenY;

    RectF mRect;

    float mXCoord, mYCoord;

    float ancho;
    float alto;

    float velInicialX;
    float velInicialY;

    float velActualX;
    float velActualY;

    boolean visible;
    int color;

    Paint paint;


    protected List<Sprite> actores=new LinkedList<>();

    public boolean isVisible() {
        return visible;
    }


    public Sprite(int screenX, int screenY){

        mScreenX=screenX;
        mScreenY=screenY;
        color=Color.argb(255, 255, 255, 255);
        visible=true;
        paint=new Paint();
    }

    public Sprite (int screenX, int screenY, int x, int y){
        mScreenX=screenX;
        mScreenY=screenY;
        color=Color.argb(255, 255, 255, 255);
        paint=new Paint();
    }
    public RectF getRect(){
        return mRect;
    }

    public void addListenerColision(Sprite s){actores.add(s);}

    public boolean colision(Sprite s){
        if (this.getRect().intersect(s.getRect())) return true;
        else return false;
    }
    public boolean colisionBordeLeft(){
        if (mRect.left<0)return true;
        return false;
    }
    public boolean colisionBordeRight(){
        if (mRect.right>mScreenX)return true;
        return false;
    }
    public boolean colisionBordeTop(){
        if (mRect.top < 0)return true;
        return false;
    }
    public boolean colisionBordeBottom(){
        if (mRect.bottom > mScreenY)return true;
        return false;
    }
    public void recolocaX(float x){
        mRect.left=x;
        mRect.right=x+ancho;
    }
    public void recolocaY(float y){
        mRect.bottom=y;
        mRect.top=y-alto;
    }
    public void recolocaXY(float x,float y){
        mRect.left=x;
        mRect.right=x+ancho;
        mRect.bottom=y;
        mRect.top=y-alto;
    }

    public  void pinta(Canvas canvas){
        if (isVisible()) {
            paint.setColor(color);
            canvas.drawRect(getRect(), paint);
        }
    }

    public abstract void reset();
    public abstract void update(GameView game, float fps);


}
