package com.studylbn.www.loopviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by LiuBin on 2017/12/3 13:53.
 */

public abstract class LoopVpAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener {

    protected int currentPosition = 0;
    protected Context mContext;
    protected ArrayList<View> views;
    protected ViewPager mViewPager;
    private LinearLayout llll;
    private ArrayList<ImageView> dots;
    private int dotnum = 0;
    private int p = 1;

    public LoopVpAdapter(Context context, ArrayList<T> datas, ViewPager viewPager, LinearLayout llll) {
        mContext = context;
        views = new ArrayList<>();
        mViewPager = viewPager;
        this.llll = llll;

        if (datas.size() > 1) {
            datas.add(0, datas.get(datas.size() - 1));
            datas.add(datas.get(1));
        }
        for (T data : datas) {
            views.add(getItemView(data));
        }
        if (views.size() >= 4) {
            dotnum = views.size() - 2;
        } else {
            dotnum = views.size();
        }
        initDot(dotnum);
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(1, false);

        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    p++;
                    if (p > views.size() - 1) {
                        p = 2;
                    }
                    if (p < 0) {
                        p = views.size() - 3;
                    }
                    mViewPager.setCurrentItem(p);
                    Message message = handler.obtainMessage(1);
                    handler.sendMessageDelayed(message, 1000);      // send message
            }
        }
    };

    private void initDot(int size) {
        dots = new ArrayList<>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(13, 13);
        layoutParams.setMargins(5, 0, 5, 1);
        for (int i = 0; i < size; i++) {
            ImageView mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(layoutParams);
            mImageView.setImageResource(R.drawable.shape_circleoff);
            dots.add(mImageView);
            llll.addView(mImageView);
        }
        dots.get(0).setImageResource(R.drawable.shape_circle);
    }

    @Override
    public int getCount() {
//        Log.e("count",views.size()+"");
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        for (int i = 0; i < dotnum; i++) {
            //将所有的圆点设置为未选中时候的图片
            dots.get(i).setImageResource(R.drawable.shape_circleoff);
        }
        //将被选中的图片中的圆点设置为被选中的时候的图片
        if (dotnum >= 4) {
            if (position == 0) {
                dots.get(dots.size() - 1).setImageResource(R.drawable.shape_circle);
            } else if (position == views.size() - 1) {
                dots.get(0).setImageResource(R.drawable.shape_circle);
            } else {
                dots.get(position - 1).setImageResource(R.drawable.shape_circle);
            }
        } else {
            dots.get(position).setImageResource(R.drawable.shape_circle);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //        若viewpager滑动未停止，直接返回
        if (state != ViewPager.SCROLL_STATE_IDLE) return;
        if (currentPosition == 0) {
            mViewPager.setCurrentItem(views.size() - 2, false);
        } else if (currentPosition == views.size() - 1) {
            mViewPager.setCurrentItem(1, false);
        }
    }

    protected abstract View getItemView(T data);
}
