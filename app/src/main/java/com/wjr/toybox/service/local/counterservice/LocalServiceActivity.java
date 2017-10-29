package com.wjr.toybox.service.local.counterservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/2/9.
 */

/**
 *      a local service provide a counter service
 */
public class LocalServiceActivity extends Activity implements View.OnClickListener{
    TextView countValue;
    Button start;
    Button stop;
    Button reset;
    CounterServiceConnection counterServiceConnection ;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_counter_layout);
        countValue = (TextView) findViewById(R.id.count_value);
        start = (Button) findViewById(R.id.count_start);
        stop = (Button) findViewById(R.id.count_stop);
        reset = (Button) findViewById(R.id.count_reset);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);

        Intent intent = new Intent(this,CounterService.class);
        counterServiceConnection = new CounterServiceConnection();
        bindService(intent,counterServiceConnection,BIND_AUTO_CREATE);

    }

    Runnable showCounterRunnable = new Runnable() {
        @Override
        public void run() {
            if (counterServiceConnection != null && counterServiceConnection.getService() != null) {
                countValue.setText(getString(R.string.count_value,counterServiceConnection.getService().getCount()));
                handler.postDelayed(showCounterRunnable, CounterService.interval);
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v == start){
            if (counterServiceConnection != null && counterServiceConnection.getService() != null){
                counterServiceConnection.getService().start();
                handler.removeCallbacks(showCounterRunnable);
                handler.post(showCounterRunnable);
            }
        }else if (v == stop){
            if (counterServiceConnection != null && counterServiceConnection.getService() != null){
                counterServiceConnection.getService().stop();
                handler.removeCallbacks(showCounterRunnable);
            }
        }else if (v == reset){
            if (counterServiceConnection != null && counterServiceConnection.getService() != null){
                counterServiceConnection.getService().reset();
                handler.removeCallbacks(showCounterRunnable);
                countValue.setText(getString(R.string.count_value,counterServiceConnection.getService().getCount()));
            }
        }
    }
}
