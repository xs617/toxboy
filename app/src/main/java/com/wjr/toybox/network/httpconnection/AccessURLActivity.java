package com.wjr.toybox.network.httpconnection;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wjr.toybox.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/14.
 */

public class AccessURLActivity extends Activity{
    TextView valueView;
    Button accessButton;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_url);
        accessButton = (Button) findViewById(R.id.access_btn);
        valueView = (TextView) findViewById(R.id.url_value);

        accessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!netWorkThread.isAlive()) {
                    netWorkThread.start();
                }
            }
        });
    }
    Thread netWorkThread = new Thread(new Runnable() {
        @Override
        public void run() {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            Reader reader = null;
            try {
                url = new URL("http://www.qq.com");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                if (url != null) {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    char[] stringBuffer = new char[1024];
                    final StringBuilder outputValue = new StringBuilder();
                    if (reader.read(stringBuffer) != -1){
                        outputValue.append(stringBuffer);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            valueView.setText(outputValue);
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (reader != null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

        }
    });

}
