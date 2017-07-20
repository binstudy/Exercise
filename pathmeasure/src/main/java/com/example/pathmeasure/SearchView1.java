package com.example.pathmeasure;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/7/20 11:49.
 */

public class SearchView1 extends View {
    private Paint mPaint;
    private int mViewWidth, mViewHight;
    private State mCurrentState = State.NONE;
    private Path path_Circle;
    private Path path_Search;
    private PathMeasure mMeasure;
    private int defaultDuration = 2000;
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    private float mAnimatorValue = 0;
    private ValueAnimator.AnimatorUpdateListener mUpdataListener;
    private Animator.AnimatorListener mAnimatorListener;

    private Handler mAnimatorHandler;
    private boolean isOver = false;

    private int count = 0;

    static enum State {
        NONE, STARTING, SEARCHING, ENDING
    }

    public SearchView1(Context context) {
        this(context, null);
    }

    public SearchView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimator();

        mCurrentState = State.STARTING;
        mStartingAnimator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHight = h;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
    }

    private void initPath() {
        mMeasure = new PathMeasure();
        float[] pos = new float[2];

        path_Circle = new Path();
        RectF oval1 = new RectF(-100, -100, 100, 100);
        path_Circle.addArc(oval1, 45, -359.9f);

        path_Search = new Path();
        RectF oval2 = new RectF(-50, -50, 50, 50);
        path_Search.addArc(oval1, 45, 359.9f);

        mMeasure.setPath(path_Circle, false);
        mMeasure.getPosTan(0, pos, null);

        path_Search.lineTo(pos[0], pos[1]);
    }

    private void initListener() {
        mUpdataListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    private void initHandler() {
        mAnimatorHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState) {
                    case STARTING:
                        //从开始动画转换到搜索动画
                        isOver = false;
                        mCurrentState = State.SEARCHING;
                        mStartingAnimator.removeAllListeners();
                        mSearchingAnimator.start();
                        break;
                    case SEARCHING:
                        if (!isOver) {
                            mSearchingAnimator.start();
                            count++;

                            if (count > 2) {
                                isOver = true;
                            }
                        } else {
                            mCurrentState = State.ENDING;
                            mSearchingAnimator.removeAllListeners();
                            mEndingAnimator.start();
                        }
                        break;
                    case ENDING:
                        mCurrentState = State.NONE;
                        break;
                }
            }
        };
    }

    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mSearchingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mEndingAnimator = ValueAnimator.ofFloat(1, 0).setDuration(defaultDuration);

        mStartingAnimator.addUpdateListener(mUpdataListener);
        mSearchingAnimator.addUpdateListener(mUpdataListener);
        mEndingAnimator.addUpdateListener(mUpdataListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    private void drawSearch(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.translate(mViewWidth / 2, mViewHight / 2);
        canvas.drawColor(Color.parseColor("#0082D7"));
        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_Search, mPaint);
                break;
            case STARTING:
                Path dst1 = new Path();
                mMeasure.setPath(path_Search, false);
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst1, true);
                canvas.drawPath(dst1, mPaint);
                break;
            case SEARCHING:

                break;
            case ENDING:
                Path dst3 = new Path();
                mMeasure.setPath(path_Search, false);
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
        }
    }
}
