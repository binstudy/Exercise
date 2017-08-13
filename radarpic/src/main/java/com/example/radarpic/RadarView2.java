package com.example.radarpic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/8/12 16:30.
 */

public class RadarView2 extends View {

    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);
    private float radius;
    private int centerX, centerY;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private int[] datas = {100, 60, 60, 60, 100, 50, 10, 20};
    private float maxValue = 100;
    private Paint mainPaint, valuePaint, textPaint;

    public RadarView2(Context context) {
        this(context, null);
    }

    public RadarView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        radius = Math.min(w, h) / 2 * 0.9f;
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

    private void drawPloygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            path.reset();
            float curR = r * i;
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
        }
        canvas.drawPath(path, mainPaint);
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            float percent = datas[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setAlpha(127);
        canvas.drawPath(path, valuePaint);
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics pf = textPaint.getFontMetrics();
        float fontHeight = pf.descent - pf.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight) * Math.sin(angle * i));
            canvas.drawText(titles[i], x, y, textPaint);
        }
    }
}
