package com.wjr.toybox.widget.behavior.textFellowBtnBehavior;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/4/1.
 */

public class TextFellowBtnBehaviorActivity extends Activity implements View.OnTouchListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_fellow_btn);
        findViewById(R.id.dependBtn).setOnTouchListener(this);
        findViewById(R.id.dependBtn2).setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                v.setX(event.getRawX() - v.getWidth() / 2);
                v.setY(event.getRawY() - v.getHeight() / 2);
                break;
        }
        return false;
    }
}
