package com.example.bezierview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by LiuBin on 2017/7/4 13:31.
 * 三阶贝赛尔曲线
 */

public class BezierView extends View {
    private Paint mPaint;
    private int centerX, centerY;
    private Point start, end, control1, control2;
    private boolean mode = true;

    public BezierView(Context context) {
        this(context, null);
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        start = new Point(0, 0);
        end = new Point(0, 0);
        control1 = new Point(0, 0);
        control2 = new Point(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control1.x = centerX - 100;
        control1.y = centerY - 200;
        control2.x = centerX + 100;
        control2.y = centerY - 200;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isMode()) {
            control1.x = (int) event.getX();
            control1.y = (int) event.getY();
        } else {
            control2.x = (int) event.getX();
            control2.y = (int) event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);

        //画辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);

        //画bezier曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        Path path = new Path();
        path.moveTo(start.x, start.y);
//        path.quadTo(control1.x, control1.y, end.x, end.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x, end.y);
        canvas.drawPath(path, mPaint);
    }
}
