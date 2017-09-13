package com.example.iconfont;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv_test = (TextView) findViewById(R.id.tv_test);
        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        tv_test.setTypeface(fromAsset);
        tv_test.setTextColor(Color.parseColor("#333333"));
        tv_test.setText("\ue6d0");
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    tv_test.setText("\ue600");
                    tv_test.setTextColor(Color.BLUE);
                } else {
                    tv_test.setText("\ue6d0");
                    tv_test.setTextColor(Color.parseColor("#333333"));
                }
                flag = !flag;
            }
        });
    }
}
