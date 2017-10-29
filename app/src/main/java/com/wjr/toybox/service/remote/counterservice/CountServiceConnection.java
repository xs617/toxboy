package com.wjr.toybox.service.remote.counterservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.wjr.toybox.databinding.ActivityCountServiceBinding;
import com.wjr.toybox.service.remote.pushmessageservice.MessageManager;
import com.wjr.toybox.service.remote.pushmessageservice.ReceiveObserver;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/10.
 */

public class CountServiceConnection implements ServiceConnection {
    ICounter iCounter;
    ActivityCountServiceBinding activityCountServiceBinding;
    Thread updateThread;
    boolean isStop = false;
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iCounter = ICounter.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isStop = true;
        updateThread = null;
        if (iCounter == null){
            return;
        }
        try {
            iCounter.stopCount();
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            iCounter = null;
        }
    }

    public void setDataBinding(ActivityCountServiceBinding dataBinding){
        this.activityCountServiceBinding = dataBinding;
    }

    public void stopCount(){
        isStop = true;
        updateThread = null;
        if (iCounter != null){
            try {
                iCounter.stopCount();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void startCount(){
        isStop =false;
        if (updateThread == null){
            updateThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!isStop ){
                        try {
                            activityCountServiceBinding.setCounter(iCounter.getCurrentCount());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            updateThread.start();
        }
    }
}
