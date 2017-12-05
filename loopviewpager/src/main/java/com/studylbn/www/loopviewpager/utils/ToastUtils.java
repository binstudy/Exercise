package com.studylbn.www.loopviewpager.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.studylbn.www.loopviewpager.R;


/**
 * Created by 知子花 on 2016/1/8.
 */
public class ToastUtils {
    private static Toast toast;

    public static void shortshow(Context context, String info) {
//        Context applicationContext = context.getApplicationContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(info);
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setGravity(Gravity.BOTTOM, 0, ABTextUtil.dip2px(context, 100));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void kechengshortshow(Context context, String info) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.kechengtoast_layout, null);
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(info);
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void loginshow(Context context, String info) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(info);
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setGravity(Gravity.BOTTOM, 0, ABTextUtil.dip2px(context, 150));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void longshow(Context context, String info) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(info);
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setGravity(Gravity.CENTER, 0, ABTextUtil.dip2px(context, 150));
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
