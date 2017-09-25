package com.studylbn.www.mybesselview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/9/24 22:52.
 */

public class MyBesselView extends View {

    private int color, mWidth, mHeight, xSize, ySize;
    private Paint paint, testPaint;
    private Path path, arcPath;
    private int xWidth, yHeight, arcHeight;
    private boolean issuccess;
    private Rect rect;
    private String text = "火箭发射成功!";

    public MyBesselView(Context context) {
        this(context, null);
    }

    public MyBesselView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBesselView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
