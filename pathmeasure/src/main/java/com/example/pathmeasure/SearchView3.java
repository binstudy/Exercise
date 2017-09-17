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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by LiuBin on 2017/7/24 23:59.
 */

public class SearchView3 extends View {

    private enum State {
        NONE, STARTING, SEARCHING, ENDING
    }

    private Paint mPaint;
    private int mWidth, mHight;
    private State mCurrentState = State.NONE;
    private Path path_Circle, path_Search;
    private PathMeasure mPathMeasure;
    private int defaultDuration = 2000;
    private float mAnimatorValue = 0;
    private ValueAnimator mStartingAnimator, mSearchingAnimator, mEndingAnimator;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;
    private Handler mAnimatorHandler;
    private boolean isOver = false;
    private int count = 0;

    public SearchView3(Context context) {
        this(context, null);
    }

    public SearchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    private void init() {
        initPaint();
        initPath();
        initListener();
        initHandler();
//        initAnimator();
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
    }

    private void initPath() {
        mPathMeasure = new PathMeasure();
        float[] pos = new float[2];
        RectF r1 = new RectF(-100, -100, 100, 100);
        RectF r2 = new RectF(-50, -50, 50, 50);
        path_Circle = new Path();
        path_Circle.addArc(r1, 45, -359.9f);
        path_Search = new Path();
        path_Search.addArc(r2, 45, 359.9f);
        mPathMeasure.setPath(path_Circle, false);
        mPathMeasure.getPosTan(0, pos, null);
        path_Search.lineTo(pos[0], pos[1]);
    }

    private void initListener() {
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

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
                                count = 0;
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

    private void drawSearch(Canvas canvas) {

    }
}
