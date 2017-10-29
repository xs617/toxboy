package com.wjr.toybox.service.local.counterservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CounterService extends Service implements Counter{
    private final String TAG = "service";
    public static final int interval = 1000;
    private Handler handler = new Handler();
    private int counter =0;
    Runnable counterRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG," :" + counter++);
            handler.postDelayed(counterRunnable,interval);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public class MyBinder extends Binder{
        public CounterService getService(){
            return CounterService.this;
        }
    }

    @Override
    public int getCount(){
        return counter;
    }

    @Override
    public void start(){
        handler.removeCallbacks(counterRunnable);
        handler.post(counterRunnable);
    }

    @Override
    public void stop(){
        handler.removeCallbacks(counterRunnable);
    }

    @Override
    public void reset(){
        handler.removeCallbacks(counterRunnable);
        counter = 0;
    }
}
