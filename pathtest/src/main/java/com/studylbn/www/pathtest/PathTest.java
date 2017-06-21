package com.studylbn.www.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/6/21 21:55.
 */

public class PathTest extends View {
    private Paint mPaint;
    private int mWidth, mHeight;

    public PathTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initConfig();
    }

    private void initConfig() {

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
//        path.moveTo(100,100);
        path.lineTo(200, 200);
        path.setLastPoint(200,100);
        path.lineTo(200, 0);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
