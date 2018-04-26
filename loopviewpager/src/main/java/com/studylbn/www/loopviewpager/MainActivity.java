package com.studylbn.www.loopviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private ArrayList<String> urls = new ArrayList<>();
    private LoopVpAdapter loopVpAdapter;
    private LinearLayout llll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        llll = (LinearLayout) findViewById(R.id.ll_ll);
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        loopVpAdapter = new ImageAdapter(this, urls, vp, llll);
    }
}
