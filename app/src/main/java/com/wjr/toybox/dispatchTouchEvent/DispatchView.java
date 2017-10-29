package com.wjr.toybox.dispatchTouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.wjr.toybox.effect.slideDeleteAbstract.SlideDeletionUtil.SLIDE_DELETE_TAG;

/**
 * Created by Administrator on 2017/2/22.
 */

public class DispatchView extends View{
    public DispatchView(Context context) {
        super(context);
    }

    public DispatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "view onTouchEvent down");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "view onTouchEvent move");
                return false;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "view onTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "view onTouchEvent cancel");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "view dispatchTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "view dispatchTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "view dispatchTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "view dispatchTouchEvent cancel");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
