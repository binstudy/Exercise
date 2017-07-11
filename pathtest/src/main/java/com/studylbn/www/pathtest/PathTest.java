package com.studylbn.www.pathtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/6/21 21:55.
 */

public class PathTest extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private int currentAngle = 0;
    private Handler handler;

    public PathTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
//        setAngle();
        handler = new Handler();
        handler.postDelayed(runnable, 1);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentAngle += 3;
            invalidate();
            handler.postDelayed(this, 5);
        }
    };

    public void setAngle() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentAngle = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
//        Path path = new Path();
//        path.moveTo(100,100);
//        path.lineTo(200, 200);
//        path.setLastPoint(200,100);
//        path.lineTo(200, 0);
//        path.close();

//        path.addRect(-100, -100, 100, 100, Path.Direction.CW);
//        path.setLastPoint(200, -200);

//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD); //设置path为奇偶原则
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        path.addRect(-400, -400, 400, 400, Path.Direction.CCW);
//        canvas.drawPath(path, mPaint);

        drawPic(canvas); //太极图
//        drawPath(canvas);
//        drawPath1(canvas);
    }

    private void drawPath1(Canvas canvas) {
        canvas.scale(1, -1);
        Path path = new Path();
        path.lineTo(100, 100);
        RectF rf = new RectF(0, 0, 300, 300);
        path.arcTo(rf, 0, 270,true);
        canvas.drawPath(path, mPaint);
    }

    private void drawPath(Canvas canvas) {

        canvas.scale(0.5f, -1);
        Path path = new Path();
        Path src = new Path();

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);
        canvas.drawPath(path, mPaint);
    }

    /**
     * 太极图
     *
     * @param canvas
     */
    private void drawPic(Canvas canvas) {
        canvas.save();
        canvas.rotate(currentAngle);

        canvas.drawColor(Color.BLUE);
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();
        Path path11 = new Path();
        Path path22 = new Path();
        Path path33 = new Path();
        Path path44 = new Path();
        Path path5 = new Path();
        Path path55 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CW);

        path11.addCircle(0, 0, 200, Path.Direction.CW);
        path22.addRect(0, -200, -200, 200, Path.Direction.CW);
        path33.addCircle(0, -100, 100, Path.Direction.CW);
        path44.addCircle(0, 100, 100, Path.Direction.CW);
        path5.addCircle(0, 100, 17, Path.Direction.CW);
        path55.addCircle(0, -100, 15, Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);

        mPaint.setColor(Color.WHITE);
        path11.op(path22, Path.Op.DIFFERENCE);
        path11.op(path33, Path.Op.DIFFERENCE);
        path11.op(path44, Path.Op.UNION);
        path11.op(path55, Path.Op.UNION);
        canvas.drawPath(path11, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawPath(path5, mPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
