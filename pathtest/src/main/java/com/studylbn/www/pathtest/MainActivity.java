package com.studylbn.www.pathtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PathTest pt = (PathTest) findViewById(R.id.pt);
        pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pt.setAngle();
            }
        });
    }
}
