package com.wjr.toybox.service.local.counterservice;

import android.content.ComponentName;
import android.os.IBinder;

/**
 * Created by Administrator on 2017/2/9.
 */

public class CounterServiceConnection implements android.content.ServiceConnection {
    private CounterService counterService = null;
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        try {
            counterService = ((CounterService.MyBinder) service).getService();
        }catch (Exception e){
            e.printStackTrace();
            counterService = null;
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        counterService = null;
    }

    public CounterService getService(){
        return counterService;
    }
}
