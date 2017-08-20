package com.example.messagertest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by LiuBin on 2017/8/18 10:22.
 */

public class MessService extends Service {
    Messenger mMessenger = new Messenger(mHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private final static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            System.out.println("MessService 收到！");
            Log.e("aaaMessService", "MessService 收到！");
            Log.e("aaaMessService", msg.what + "");
            Messenger messenger = msg.replyTo;
            Message msg_reply = Message.obtain();
            msg_reply.what = 222;
            try {
                messenger.send(msg_reply);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
