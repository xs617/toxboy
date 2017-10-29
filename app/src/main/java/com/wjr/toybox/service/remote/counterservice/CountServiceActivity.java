package com.wjr.toybox.service.remote.counterservice;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wjr.toybox.R;
import com.wjr.toybox.databinding.ActivityCountServiceBinding;
import com.wjr.toybox.service.remote.pushmessageservice.Message;
import com.wjr.toybox.service.remote.pushmessageservice.MessageManagerService;
import com.wjr.toybox.service.remote.pushmessageservice.ReceiveObserver;
import com.wjr.toybox.service.remote.pushmessageservice.SentMessageServiceConnection;

/**
 * Created by Administrator on 2017/2/10.
 */

public class CountServiceActivity extends Activity{
    CountServiceConnection countServiceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityCountServiceBinding activityCountServiceBinding =  DataBindingUtil.setContentView(this,R.layout.activity_count_service);
//        activityCountServiceBinding.setCounter(new Count(0,1000));

        countServiceConnection= new CountServiceConnection();
        Intent intent = new Intent();
        intent.setClass(this,CountService.class);
        bindService(intent,countServiceConnection,BIND_AUTO_CREATE);
        countServiceConnection.setDataBinding(activityCountServiceBinding);

        activityCountServiceBinding.setStartClickLsr(new onStartClickListener());
        activityCountServiceBinding.setStopClickLsr(new onStopClickListener());
    }
    public class onStartClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            countServiceConnection.startCount();
        }
    }
    public class onStopClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            countServiceConnection.stopCount();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(countServiceConnection);
    }
}
