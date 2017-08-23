package com.example.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
    private Product product;
    private Binder mBinder = new IMyAidlInterface.Stub() {
        @Override
        public Product buy() throws RemoteException {
            return product;
        }

        @Override
        public void setProduct(Product product) throws RemoteException {
            AIDLService.this.product = product;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
