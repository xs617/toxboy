package com.wjr.toybox.effect.slideDeleteAbstract;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import static com.wjr.toybox.effect.slideDeleteAbstract.SlideDeletionUtil.SLIDE_DELETE_TAG;

/**
 * Created by Administrator on 2017/2/22.
 */

/**
 * 这个并没有做任何特殊处理，只是打印事件确保流程无误
 */
public class SlideDeletionDeleteView extends TextView{
    public SlideDeletionDeleteView(Context context) {
        this(context, null, 0);
    }

    public SlideDeletionDeleteView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideDeletionDeleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "delete touch down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "delete touch move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "delete touch up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "delete touch cancel");
                break;
        }
        //默认情况下会消费掉事件并回调onClick之类的回调函数
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "delete dispatch down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "delete dispatch move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "delete dispatch up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "delete dispatch cancel");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
