package com.example.pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
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
        drawCoordinate(canvas); //坐标
//        drawPathMeasure(canvas);
//        drawPathMeasure2(canvas);
        drawPathMeasure3(canvas);
    }

    private void drawPathMeasure3(Canvas canvas) {
        Path path1 = new Path();
        Path path2 = new Path();
        RectF r1 = new RectF(-100, -100, 100, 100);
        RectF r2 = new RectF(-200, -200, 200, 200);
        path1.addRect(r1, Path.Direction.CW);
        path2.addRect(r2, Path.Direction.CW);
        path1.addPath(path2);
        canvas.drawPath(path1, mPaintBlack);

        PathMeasure pm = new PathMeasure(path1, false);
        Log.e("length1", pm.getLength() + "");
        pm.nextContour();
        Log.e("length2", pm.getLength() + "");
    }

    private void drawPathMeasure2(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(-200, -200, 200, 200);
        path.addRect(rectF, Path.Direction.CW);
        Path dst = new Path();
        dst.moveTo(-300, -300);
        dst.lineTo(0, 0);
        PathMeasure pm = new PathMeasure(path, false);
        pm.getSegment(200, 600, dst, false);
        canvas.drawPath(dst, mPaintBlack);
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
