package com.example.testbessel;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by LiuBin on 2017/9/25 16:21.
 */

public class MyBesselView1 extends View {

    private int color, mWidth, mHeight, xSize, ySize;
    private Paint paint, textPaint;
    private Path path, arcPath;
    private int xWidth, yHeight, arcHeight;
    private boolean isSuccess;
    private Rect mRect;
    private String text = "火箭发射成功!!!";

    public MyBesselView1(Context context) {
        this(context, null);
    }

    public MyBesselView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBesselView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyBesselView1, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.MyBesselView1_titleColor) {
                color = typedArray.getColor(index, Color.BLACK);
            }
        }
        typedArray.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        path = new Path();
        arcPath = new Path();
        mRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = widthSize * 1 / 2;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = heightSize * 1 / 2;
        }

        mWidth = width;
        mHeight = height;
        xWidth = mWidth * 1 / 10;
        yHeight = mHeight * 1 / 8;
        arcHeight = mHeight * 7 / 10;

        setMeasuredDimension(width, height);
    }
}
