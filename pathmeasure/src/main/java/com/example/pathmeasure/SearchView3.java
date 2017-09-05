package com.example.pathmeasure;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
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

    private void init() {

    }
}
