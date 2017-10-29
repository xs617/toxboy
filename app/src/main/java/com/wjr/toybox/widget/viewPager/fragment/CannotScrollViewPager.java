package com.wjr.toybox.widget.viewPager.fragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/2/16.
 */

public class CannotScrollViewPager extends ViewPager{
    boolean isCanScroll = false;

    public CannotScrollViewPager(Context context) {
        super(context);
    }

    public CannotScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }
}
