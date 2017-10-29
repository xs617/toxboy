package com.wjr.toybox.service.remote.pushmessageservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MessageServiceActivity extends Activity{
    private TextView messageView;
    SentMessageServiceConnection sentMessageServiceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_message_service);
        messageView = (TextView) findViewById(R.id.message);

        sentMessageServiceConnection = new SentMessageServiceConnection();
        Intent intent = new Intent();
        intent.setClass(this,MessageManagerService.class);
        bindService(intent,sentMessageServiceConnection,BIND_AUTO_CREATE);
        sentMessageServiceConnection.addReceivedObserver(new ReceiveObserver.Stub() {
            @Override
            public void notifyReceive(Message message) {
                messageView.setText(message.getMessage());
            }
        });
    }
}
