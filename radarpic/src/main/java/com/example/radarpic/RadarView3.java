package com.example.radarpic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/8/22 20:53.
 */

public class RadarView3 extends View {
    private int count = 6; //多边形的边数
    private float angle = (float) (2 * Math.PI / count);
    private float radius;
    private int mCenterX;
    private int mCenterY;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};
    private int maxValue;
    private Paint mainPaint;
    private Paint valuePaint;
    private TextPaint textPaint;

    public RadarView3(Context context) {
        this(context, null);
    }

    public RadarView3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        count = Math.min(titles.length, data.length);
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setColor(Color.GRAY);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new TextPaint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(w, h) / 2 * 0.9f;
        mCenterX = w / 2;
        mCenterY = h / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPloygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {

    }

    private void drawRegion(Canvas canvas) {

    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + radius * Math.cos(angle * i));
            float y = (float) (mCenterY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
        }
        canvas.drawPath(path, mainPaint);
    }

    private void drawPloygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            path.reset();
            float curR = r * i;
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(mCenterX + curR, mCenterY);
                } else {
                    float x = (float) (mCenterX + curR * Math.cos(angle * j));
                    float y = (float) (mCenterY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }


}
