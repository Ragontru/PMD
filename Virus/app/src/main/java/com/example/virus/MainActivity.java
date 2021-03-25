package com.example.virus;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends Activity {

    Pong pong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        pong = new Pong(this, size.x, size.y);

        setContentView(pong);
    }

        @Override
        protected void onResume() {
            super.onResume();
            pong.resume();
        }

        @Override
        protected void onPause() {
            super.onPause();
            pong.pause();
        }
    }