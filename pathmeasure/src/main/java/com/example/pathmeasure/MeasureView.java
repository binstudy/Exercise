package com.example.pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by LiuBin on 2017/7/10 11:18.
 */

public class MeasureView extends View {
    private int mViewWidth, mViewHeight;
    private Paint mPaint, mPaintBlack;

    public MeasureView(Context context) {
        this(context, null);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaintBlack = new Paint();
        mPaintBlack.setColor(Color.BLACK);
        mPaintBlack.setStrokeWidth(4);
        mPaintBlack.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        drawCoordinate(canvas);
        drawPathMeasure(canvas);
    }

    private void drawPathMeasure(Canvas canvas) {
        Path path = new Path();
        path.lineTo(0, 200);
        path.lineTo(200, 200);
        path.lineTo(200, 0);
        PathMeasure measure1 = new PathMeasure(path, false);
        PathMeasure measure2 = new PathMeasure(path, true);
        Log.e("false", measure1.getLength() + "");
        Log.e("true", measure2.getLength() + "");
        canvas.drawPath(path, mPaintBlack);
    }

    private void drawCoordinate(Canvas canvas) {
        Path path = new Path();
        path.moveTo(-(mViewWidth / 2), 0);
        path.lineTo(mViewWidth / 2, 0);
        path.moveTo(0, -(mViewHeight / 2));
        path.lineTo(0, mViewHeight / 2);
        canvas.drawPath(path, mPaint);
    }
}
