package com.wjr.toybox.service.remote.pushmessageservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MessageManagerService extends Service {
    ArrayList<ReceiveObserver> receiveObservers = new ArrayList<ReceiveObserver>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("life","onCreate---");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("life","onBind---");
        return new MessageManager.Stub(){


            @Override
            public void sent(Message message) throws RemoteException {
                Log.e("service",message.toString());
                for (ReceiveObserver receiveObserver : receiveObservers){
                    if (receiveObserver != null) {
                        receiveObserver.notifyReceive(message);
                    }
                }
            }

            @Override
            public void addReceiveObserver(ReceiveObserver receiveObserver) throws RemoteException {
                receiveObservers.add(receiveObserver);
            }
        };
    }
}
