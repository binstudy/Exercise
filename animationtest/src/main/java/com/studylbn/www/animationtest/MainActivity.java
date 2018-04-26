package com.studylbn.www.animationtest;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_tv = findViewById(R.id.tv_tv);
        ObjectAnimator backgroundColor =
                ObjectAnimator.ofObject(tv_tv, "textColor", new ArgbEvaluator(), Color.BLACK, Color.BLUE, Color.RED);
//        backgroundColor.setDuration(1000);
        backgroundColor.setRepeatCount(ObjectAnimator.INFINITE);
        backgroundColor.setRepeatMode(ObjectAnimator.RESTART);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_tv, "textSize", 20, 50, 30, 20, 10);
//        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(backgroundColor, objectAnimator);
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
}
