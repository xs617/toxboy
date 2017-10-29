package com.wjr.toybox.dispatchTouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static com.wjr.toybox.effect.slideDeleteAbstract.SlideDeletionUtil.SLIDE_DELETE_TAG;

/**
 * Created by Administrator on 2017/2/22.
 */

public class DispatchLinearLayout extends LinearLayout{
    public DispatchLinearLayout(Context context) {
        super(context);
    }

    public DispatchLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "viewGroup dispatchTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "viewGroup dispatchTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "viewGroup dispatchTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "viewGroup dispatchTouchEvent cancel");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onInterceptTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onInterceptTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onInterceptTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onInterceptTouchEvent cancel");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "viewGroup onTouchEvent cancel");
                break;
        }
        return super.onTouchEvent(event);
    }
}
