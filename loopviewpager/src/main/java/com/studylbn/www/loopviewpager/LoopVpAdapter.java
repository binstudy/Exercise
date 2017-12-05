package com.studylbn.www.loopviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.studylbn.www.loopviewpager.utils.ABTextUtil;
import com.studylbn.www.loopviewpager.utils.ToastUtils;

import java.util.ArrayList;

import static android.R.id.message;

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
    private int clickPosition = 0;

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

        initEvent(viewPager);
        Message message = handler.obtainMessage(1);     // Message
        handler.sendMessageDelayed(message, 1000);
    }

    private void initEvent(ViewPager viewPager) {
        final long[] currentTime = {0};
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentTime[0] = SystemClock.uptimeMillis();
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (SystemClock.uptimeMillis() - currentTime[0] <= 400) {
                            clickPosition = currentPosition;
                            ToastUtils.shortshow(mContext, clickPosition + "");
                        }
                        Message message = handler.obtainMessage(1);     // Message
                        handler.sendMessageDelayed(message, 1000);
                        break;
                }
                return false;
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    currentPosition++;
                    if (currentPosition > views.size() - 1) {
                        currentPosition = 2;
                    }
                    if (currentPosition < 0) {
                        currentPosition = views.size() - 3;
                    }
                    mViewPager.setCurrentItem(currentPosition);
                    Message message = handler.obtainMessage(1);
                    handler.sendMessageDelayed(message, 1000);      // send message
            }
        }
    };

    private void initDot(int size) {
        dots = new ArrayList<>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ABTextUtil.dip2px(mContext, 10), ABTextUtil.dip2px(mContext, 10));
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
            currentPosition = views.size() - 2;
            mViewPager.setCurrentItem(currentPosition, false);
        } else if (currentPosition == views.size() - 1) {
            currentPosition = 1;
            mViewPager.setCurrentItem(currentPosition, false);
        }
    }

    protected abstract View getItemView(T data);
}
