package com.example.pathmeasure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final SearchView2 sv = (SearchView2) findViewById(R.id.sv_sv);
//        sv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sv.init();
//            }
//        });
        //手势检测器
        gestureDetector = new GestureDetector(this, new MyOnGestureListener());
        findViewById(R.id.tv_tv).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //速度跟踪
        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        int xVelocity = (int) velocityTracker.getXVelocity();
        int yVelocity = (int) velocityTracker.getYVelocity();
        Log.e("xy", xVelocity + " " + yVelocity);
        return super.onTouchEvent(event);
    }

    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("onFling","onFling");
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.e("onDown","onDown");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.e("onDoubleTap","onDoubleTap");
            return false;
        }
    }
}
