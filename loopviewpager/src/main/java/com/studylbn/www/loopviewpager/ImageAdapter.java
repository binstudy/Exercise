package com.studylbn.www.loopviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by LiuBin on 2017/12/3 18:25.
 */

public class ImageAdapter extends LoopVpAdapter<String> {
    private ViewGroup.LayoutParams layoutParams;
    private int p = 0;

    public ImageAdapter(Context context, ArrayList<String> datas, ViewPager viewPager, LinearLayout llll) {
        super(context, datas, viewPager, llll);
    }

    @Override
    protected View getItemView(String data) {
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(data).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        return imageView;
    }
}
