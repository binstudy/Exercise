package com.example.radarpic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/7/3 11:42.
 */

public class RadarView extends View {

    private int count = 6;
    private float angle = (float) (Math.PI * 2 / count);
    private float radius;
    private int centerX;
    private int centerY;
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float maxValue = 100;             //数据最大值
    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private TextPaint textPaint;                //文本画笔

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        count = Math.min(titles.length, data.length);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(h, w) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawRegion(canvas);
        drawText(canvas);
    }

    /**
     * 绘制多边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
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

    /**
     * 绘制线
     *
     * @param canvas
     */
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

    /**
     * 绘制数值区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
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
        valuePaint.setAlpha(127);
        valuePaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, valuePaint);
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x, y;
            if (i == 0 || i == titles.length - 1 || i == titles.length - 2) {
                x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
                y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));

            } else {
                x = (float) (centerX + (radius + fontHeight) * Math.cos(angle * i));
                y = (float) (centerY + (radius + fontHeight) * Math.sin(angle * i));
            }
            canvas.drawText(titles[i], x, y, textPaint);
        }

        //下面是Demo的方法
//        for (int i = 0; i < count; i++) {
//            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
//            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
//            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
//                canvas.drawText(titles[i], x, y, textPaint);
//            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第1象限
//                canvas.drawText(titles[i], x, y, textPaint);
//            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第2象限
//                float dis = textPaint.measureText(titles[i]);//文本长度
//                canvas.drawText(titles[i], x - dis, y, textPaint);
//            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第3象限
//                float dis = textPaint.measureText(titles[i]);//文本长度
//                canvas.drawText(titles[i], x - dis, y, textPaint);
//            }
//        }
    }
}
