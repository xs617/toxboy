package com.wjr.toybox.effect.slideDeleteAbstract;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

import static com.wjr.toybox.effect.slideDeleteAbstract.SlideDeletionUtil.SCROLL_MIN_DISTANCE;

/**
 * Created by Administrator on 2017/2/20.
 */

public class SlideDeletionListView extends ListView {
    boolean isScrollHorizontal = false;

    private float downX;
    private float downY;
    Context context;
    public SlideDeletionListView(Context context) {
        this(context,null,0);
    }

    public SlideDeletionListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideDeletionListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }




    /**
     *
     * 拦截垂直滑动事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"intercept down" );
                downX = ev.getX();
                downY = ev.getY();
                isScrollHorizontal = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"intercept move");
                final float deltaX = Math.abs(downX - ev.getX());
                final float deltaY = Math.abs(downY - ev.getY());
                //如果判断事件是水平滑动，那么之后都将是水平滑动，垂直滑动同理
                //这么做是为了防止如果滑到一半滑动delta比例改变使得滑动不连续
                //这里只需要对水平滑动做额外处理，因为我们本来就要拦截垂直滑动事件
                //拦截返回true后拦截方法之后就不会再被调用，故不需要额外处理。
                if (isScrollHorizontal){
                    return false;
                }else{
                    //拦截垂直滑动事件
                    if (deltaY >= SCROLL_MIN_DISTANCE &&  Math.atan(Math.abs(deltaY / deltaX)) >= 3.14 / 16 *3){
                        return true;
                    }
                    if (deltaX >= SCROLL_MIN_DISTANCE &&  Math.atan(Math.abs(deltaY / deltaX )) < 3.14 / 16 *3){
                        isScrollHorizontal = true;
                    }
                    //将水平滑动，点击子项，点击子项中的控件交给子项处理
                    return false;
                }
            case MotionEvent.ACTION_UP:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"intercept up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"intercept cancel");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"touch down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"touch move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"touch up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SlideDeletionUtil.SLIDE_DELETE_TAG,"touch cancel");
                break;
        }
        return super.onTouchEvent(ev);
    }
}
