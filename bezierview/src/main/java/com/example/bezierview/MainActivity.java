package com.example.bezierview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup rgSelect = (RadioGroup) findViewById(R.id.rg_select);
        final BezierView bv = (BezierView) findViewById(R.id.bv);
        final RadioButton rbleft = (RadioButton) findViewById(R.id.rb_left);
        final RadioButton rbright = (RadioButton) findViewById(R.id.rb_right);
        rbleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bv.setMode(true);
            }
        });
        rbright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bv.setMode(false);
            }
        });

    }
}
