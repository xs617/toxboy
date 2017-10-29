package com.wjr.toybox.socket;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wjr.toybox.R;

public class SocketActivity extends AppCompatActivity implements IMessageView {
    TextView textView;
    IPresenter iPresenter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_v);
        textView = (EditText) findViewById(R.id.message);
        button = (Button) findViewById(R.id.send);

        iPresenter = new MessagePresenter(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.sendMessage();
            }
        });

    }

    Handler handler = new Handler();

    @Override
    public void showMessage(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(message);
            }
        });
    }

    @Override
    public String getMessage() {
        return textView.getText().toString();
    }
}
