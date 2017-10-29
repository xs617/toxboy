package com.wjr.toybox.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.wjr.toybox.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/14.
 */

public class DownLoadFileActivity extends Activity {
    ProgressBar progressBar = null;
    Button startBtn = null;
    Button stopBtn= null;
    TextView maxLengthView;
    TextView currentLengthView;
    String Tag = "download";
    int maxLength = 0;
    int currentLength = 0;
    int start = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_file);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        startBtn = (Button) findViewById(R.id.download_btn);
        stopBtn = (Button) findViewById(R.id.stop);
        maxLengthView = (TextView) findViewById(R.id.max_size);
        currentLengthView = (TextView) findViewById(R.id.current_size);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downLoadTask == null){
                    downLoadTask = new DownLoadTask();
                }
                if(downLoadTask.getStatus() == AsyncTask.Status.RUNNING){
                    Toast.makeText(DownLoadFileActivity.this,"下载任务正在执行，请不要耐心等待",Toast.LENGTH_SHORT).show();
                }else{
                    String url ="http://attach.bbs.miui.com/forum/201502/03/150905vpzrbnzksnkbkyhr.jpg";
                    downLoadTask.execute(url);
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(downLoadTask != null) {
                    downLoadTask.cancel(true);
                    downLoadTask = null;
                }
            }
        });
    }

    AsyncTask<String,Integer,Integer> downLoadTask  =null;
    class DownLoadTask extends  AsyncTask<String, Integer, Integer>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... params) {
            FileOutputStream fileOutputStream = null;
            BufferedInputStream bufferedInputStream = null;
            HttpURLConnection httpURLConnection = null;

            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestProperty("range","bytes="+currentLength+"-");
                start = currentLength;
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                maxLength = httpURLConnection.getContentLength();
                File path = new File(Environment.getExternalStorageDirectory() + File.separator + "myDownloadPng");
                if (!path.exists()){
                    if (!path.mkdirs()){
                        return 0;
                    }
                }
                File file = new File(path + File.separator + "test.png");
                fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                int readSize;
                while (!isCancelled()) {
                    if((readSize = bufferedInputStream.read(buffer)) == -1){
                        break;
                    }
                    fileOutputStream.write(buffer);
                    publishProgress(readSize);
                }
                fileOutputStream.flush();

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                    if (httpURLConnection != null){
                        httpURLConnection.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return 1;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progress = values[0];
            if (progressBar == null){
                return;
            }
            if (maxLength == -1){
                return;
            }else {
                currentLength += progress;
                final int max =  start + maxLength;
                progressBar.setMax(max);
                progressBar.setProgress(currentLength);
                currentLengthView.setText(""+currentLength);
                maxLengthView.setText(""+ max);
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.e(Tag,"onPostExecute");
            if (integer == 0){
                Toast.makeText(DownLoadFileActivity.this,"本地文件创建失败",Toast.LENGTH_SHORT).show();
            }else{
                downLoadTask = null;
                currentLength = 0;
                maxLength = -1;
            }
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            Log.e(Tag,"cancel");
            downLoadTask = null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(Tag,"cancel");
            downLoadTask = null;
        }
    } ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downLoadTask != null){
            downLoadTask.cancel(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
