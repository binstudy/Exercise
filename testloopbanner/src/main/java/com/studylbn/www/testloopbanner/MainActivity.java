package com.studylbn.www.testloopbanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.studylbn.www.loopbanner.BannerConfig;
import com.studylbn.www.loopbanner.LoopBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LoopBanner banner1, banner2, banner3;
    List<String> urls = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner1 = (LoopBanner) findViewById(R.id.banner1);
        banner2 = (LoopBanner) findViewById(R.id.banner2);
        banner3 = (LoopBanner) findViewById(R.id.banner3);
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urls.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        titles.add("51巅峰钜惠");
        titles.add("十大星级品牌联盟，全场2折起");
        titles.add("生命不是要超越别人，而是要超越自己。");
        titles.add("己所不欲，勿施于人。——孔子");
        titles.add("嗨购5折不要停");

        banner1.setImages(urls)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner2.setImages(urls)
                .setImageLoader(new GlideImageLoader())
                .start();

        banner3.setImages(urls)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();

    }
}
