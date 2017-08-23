package com.example.aidltest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.aidltest.R.id.btn_aidl;
import static com.example.aidltest.R.id.btn_unbind;

public class MainActivity extends Activity {

    private Intent mIntent;
    private TestConnection mTestConnection;
    private IMyAidlInterface mIMyAidlInterface;
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initEvent();
    }

    private void init() {
        mProduct = new Product();
        mProduct.name = "cleader";
        mProduct.price = 777;
        mIntent = new Intent(this, AIDLService.class);
        mTestConnection = new TestConnection();
        bindService(mIntent, mTestConnection, BIND_AUTO_CREATE);
    }

    private void initEvent() {
        findViewById(R.id.btn_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    mIMyAidlInterface.sell();
                    if (mIMyAidlInterface != null) {
                        mIMyAidlInterface.setProduct(mProduct);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //从以下代码可知只要拿到aidlservice的对象与ServiceConnection是否bind或unbind无关。
                    // unbind后也还可以使用aidlservice的对象。
//                    mIMyAidlInterface.unsell();
                    Log.e("Product", mIMyAidlInterface.buy().toString());
                    if (mTestConnection != null) {
                        unbindService(mTestConnection);
                        mTestConnection = null;
                        Log.e("mainactivity", mTestConnection + "");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class TestConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("MainActivity", "已连接");
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
//            try {
////                mIMyAidlInterface.sell();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("MainActivity", "断开连接");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mTestConnection);
    }
}
