package com.example.messagertest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Messenger messengeReplyTo = new Messenger(handler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MessService.class);
        MyServiceConnection myServiceConnection = new MyServiceConnection();
        bindService(intent, myServiceConnection, BIND_AUTO_CREATE);
    }

    public class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain();
            message.what = 111;
            message.replyTo = messengeReplyTo;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private final static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            System.out.println("MainActivity 收到！");
            Log.e("aaaMainActivity", "MainActivity 收到！");
            Log.e("aaaMainActivity", msg.what + "");
        }
    };
}
