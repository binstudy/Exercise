package com.example.testbessel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.path;
import static android.R.attr.y;

/**
 * Created by LiuBin on 2017/9/25 14:41.
 */

public class MyBesselView extends View {
    private int xBessel, yBessel, mWidth, mHeight;
    private Paint mPaint;
    private Path path;

    public MyBesselView(Context context) {
        this(context, null);
    }

    public MyBesselView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBesselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        path.moveTo(mWidth * 1 / 8, mHeight * 1 / 5);
        path.quadTo(xBessel, yBessel, mWidth * 7 / 8, mHeight * 1 / 5);
        canvas.drawPath(path, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(xBessel, yBessel, 10, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        xBessel = w / 2;
        yBessel = mHeight * 1 / 5 + 200;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                xBessel = x;
                yBessel = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return true;
    }
}
