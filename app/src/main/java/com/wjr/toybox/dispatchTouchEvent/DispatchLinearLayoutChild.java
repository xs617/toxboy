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

public class DispatchLinearLayoutChild extends LinearLayout{
    public DispatchLinearLayoutChild(Context context) {
        super(context);
    }

    public DispatchLinearLayoutChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchLinearLayoutChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 dispatchTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 dispatchTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 dispatchTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 dispatchTouchEvent cancel");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onInterceptTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onInterceptTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onInterceptTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onInterceptTouchEvent cancel");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onTouchEvent down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onTouchEvent move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onTouchEvent up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "viewGroup2 onTouchEvent cancel");
                break;
        }
        return super.onTouchEvent(event);
    }
}
