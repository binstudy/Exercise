package com.example.circleprogress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.circleprogress.utils.Constant;
import com.example.circleprogress.utils.MiscUtil;

/**
 * Created by LiuBin on 2017/6/3 12:01.
 */

public class DialProgress extends View {

    private static final String TAG = DialProgress.class.getSimpleName();
    private Context mContext;
    private boolean antiAlias;

    //圆心坐标
    private Point mCenterPoint;
    private float mRadius;
    private float mTextOffsetPercentInRadius;

    //绘制提示
    private TextPaint mHintPaint;
    private CharSequence mHint;
    private int mHintColor;
    private float mHintSize;
    private float mHintOffset;

    //绘制数值
    private Paint mValuePaint;
    private int mValueColor;
    private float mMaxValue;
    private float mValue;
    private float mValueSize;
    private float mValueOffset;
    private String mPrecisionFormat;

    //绘制单位
    private Paint mUnitPaint;
    private float mUnitSize;
    private int mUnitColor;
    private float mUnitOffset;
    private CharSequence mUnit;
    //前景圆弧
    private Paint mArcPaint;
    private float mArcWidth;
    private int mDialIntervalDegree;
    private float mStartAngle, mSweepAngle;
    private RectF mRectF;
    //渐变
    private int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};
    //当前进度，[0.0f,1.0f]
    private float mPercent;
    //动画时间
    private long mAnimTime;
    //属性动画
    private ValueAnimator mAnimator;

    //背景圆弧
    private Paint mBgArcPaint;
    private int mBgArcColor;

    //刻度线颜色
    private Paint mDialPaint;
    private float mDialWidth;
    private int mDialColor;

    private int mDefaultSize;

    public DialProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mCenterPoint = new Point();
        mDefaultSize = MiscUtil.dipToPx(context, Constant.DEFAULT_SIZE);
        mRectF = new RectF();
        initConfig();
        initPaint();
    }

    private void initConfig() {

    }

    private void initPaint() {

    }


}
