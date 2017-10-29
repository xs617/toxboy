package com.wjr.toybox.effect.slideDeletion;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.wjr.toybox.R;

import static com.wjr.toybox.effect.slideDeleteAbstract.SlideDeletionUtil.SCROLL_MIN_DISTANCE;
import static com.wjr.toybox.effect.slideDeleteAbstract.SlideDeletionUtil.SLIDE_DELETE_TAG;

/**
 * Created by Administrator n 2017/2/22.
 */

public class SlideDeletionLinearLayout extends LinearLayout{
    private float downX;
    private float lastX;
    private boolean isScrollHorizontal = false;
    private Scroller scroller;
    public static int SCROLL_TIME = 1000;

    public SlideDeletionLinearLayout(Context context) {
        this(context, null, 0);
    }

    public SlideDeletionLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideDeletionLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller= new Scroller(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "layout dispatch down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "layout dispatch move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "layout dispatch up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "layout dispatch cancel");
                break;
        }
        return super.dispatchTouchEvent(event);
    }


    /***
     * 在item的layout中处理水平滑动。
     * 因为在listView中已经拦截了垂直滑动，
     * 所以到listView子项的时候肯定是不存在垂直滑动的事件的，
     * 这里需要处理的是进行了水平滑动还是点击事件。
     *
     *
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        lastX = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "layout intercept down");
                downX = event.getX();
                isScrollHorizontal = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "layout intercept move");
                final float deltaX = Math.abs(downX - event.getX());
                if (deltaX > SCROLL_MIN_DISTANCE) {
                    isScrollHorizontal = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "layout intercept up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "layout intercept cancel");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }


    /**
     * 这里有两种情况，
     * 其一：由onInterceptTouchEven返回true后获得的水平滑动事件，
     * 根据移动距离scroll,松手时播放动画完全展开或收起
     * 其二：onInterceptTouchEven返回false或者super.，直到向上传递过程仍然没有view处理了该事件，
     * 此时获得事件，这里就不需要进行额外处理了，垂直滑动由listView拦截处理、
     * 子项控件触摸事件由子项控件处理、水平滑动由其本身拦截处理，
     * 只要继续向上传，到达listView处，等listView调用super.来处理子项点击即可
     *
     * 另一个关键点是
     * 我们必须让最后一个View消费掉Down事件，也就是让所有事件都经过整个View的层次结构（触摸范围内，在触摸范围外的view不会执行回调），
     * 从而能让事件在合适的位置被拦截或处理
     * 这个前提遇到了一个关键的问题
     * 由于触摸事件有可能没有经过deleteView，这导致item的layout有可能成为传递事件回调过程中最后一个。
     * 我们必须在这种情况下让item的layout消费掉这个down事件，使得后续事件能够继续到达item的layout，
     * 如果不这么做，touch事件将一直向上传递,可能会被父View消费而使得之后的事件不再经过itemLayout,
     * 就本例而言，listView的父类AbstractListView的OnTouchEvent方法返回true，导致后续事件全部到listView上去了
     *
     * 为了达到上述目的，我们在item layout的onTouchEvent中消费掉Down事件，
     * 两种情况下
     * 一、item layout为最底View，在向下传递过程中不可能对Down事件进行拦截的（因为我们在move事件中才进行拦截）。
     * 那么就会在向上传递中到达OnTouchEvent，此时Down事件被消费，满足了以上条件
     *
     *二、item layout为中间层View，同上在向下传递过程中不可能对Down事件进行拦截的，Down事件被一直传递到最底层的Veiw,
     * 然后被消费（这里当然也是我们让它返回true消费掉），那么Down事件就不可能再经过item layout的onTouchEvent了,
     * 所以这个就算写了return true也不影响，同时也满足在最底层View消费Down事件的条件。
     *
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(SLIDE_DELETE_TAG, "layout touch down");
                lastX = event.getX();
                scroller.abortAnimation();
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e(SLIDE_DELETE_TAG, "layout touch move");
                //随手指滑动view，一旦开始了滑动，在整套事件结束前都只能滑动
                if (isScrollHorizontal || Math.abs(downX - event.getX()) >SCROLL_MIN_DISTANCE) {
                    isScrollHorizontal = true;
                    scrollByFinger(event.getX());
                    lastX = event.getX();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(SLIDE_DELETE_TAG, "layout touch up");
                if (!isScrollHorizontal){
                    //不是水平滑动,直接收起deleteBtn
                    //当onTouchEvent能收到up事件，说明点击事件发生在deleteView之外
                    // (如果发生在deleteView上面，那么deleteView就会消费掉down事件，
                    //  deleteView的onTouchEvent会先于layout到OnTouchEvent获取到up事件（因为layout没有拦截up事件），
                    // 因而此处代码就不会被执行，反证完毕)，那么这时候如果layout有进行过滚动就滚回去， 如果没有滚动就执行点击事件。
                    //
                    // 这里有些遗憾的是由于下行过程中listView无法得知点击的是子项还是deleteView,而上行过程中点击事件会在到达listView前被消费，
                    // 我们不得不将点击子项的处理留到这里来做，这意味着listView的onItemClick等方法将不会生效
                    if (getScrollX() >0 ){
                        //收起来
                        scroller.abortAnimation();
                        scroller.startScroll(getScrollX(),0,getScrollX()*-1,0,getShrinkDuration());
                        invalidate();
                        setIsSlide(false);
                    }else{
                        //item的点击事件
                        callOnClick();
                        setIsSlide(false);
                    }
                }else{
                    //水平滑动，按照滑出的比例播放动画完全展开或收起动画
                    if (getScrollX() < getDeleteViewWidth() / 2 ){
                        //收起来
                        scroller.abortAnimation();
                        scroller.startScroll(getScrollX(),0,getScrollX() *-1,0, getShrinkDuration());
                        invalidate();
                        setIsSlide(false);
                    }else{
                        //展开
                        scroller.abortAnimation();
                        scroller.startScroll(getScrollX(),0,getDeleteViewWidth() - getScrollX(),0,getStretchDuration());
                        invalidate();
                        setIsSlide(true);
                    }
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                Log.e(SLIDE_DELETE_TAG, "layout touch cancel");
                break;
        }
        lastX = event.getX();
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }

    /**
     * 随手指滑动
     *
     * 首先明确scroll和坐标之间的关系
     * scroll接受的参数是以最初的视窗坐标原点为坐标原点的坐标系，
     * 而参数的值则表达了视窗移动的距离。
     * 例如传入x为-100，结果等价的效果是视窗向左移动，即等价效果为View向右移动100
     * 因此，当我们期待view向右滚动，就应该给scroll传入一个负值，而期待view向左滚，则应该传入正值
     * 相对的如果scrollX值为正值，表现为View向左滚动，ScrollX的值为负值，表现为View向右滚动
     * 而已经向右移了的view,getScrollX值为负，已经向左移的view，getScrollX为正

     * 非常幸运的是在这里一旦存在滚动，ScrollX必然是大于0
     * 因为当delete已经完全被收起的时候再继续左移是不被允许的
     * 所以我们不用对ScrollX>0,<0进行额外处理
     *
     * @param currentX
     */
    private void scrollByFinger(float currentX){
        final float deltaX = currentX - lastX;
        int maxScrollDistance = getDeleteViewWidth();
        final int scrollX;
        getScrollX();

        if (deltaX > 0){
            //向右边划时，我们期待View向右边滚动，
            //向右滚动的最大值实际上就是已经滚动的值
            //不过因为要使得View向右滚，所以传的参数应该是负值
            if (deltaX > getScrollX()){
                scrollX = getScrollX() *-1;
            }else{
                scrollX = (int) deltaX *-1;
            }
        }else{
            //向左边划时，我们期待View向左边滚动，
            //向左滚动的最大值实际上就是还没有滚出来的值，即maxScrollDistance - getScrollX()
            //不过因为要使得View向左滚，所以传的参数应该是正值
            if (deltaX <  (maxScrollDistance - getScrollX()) *-1){
                scrollX =  maxScrollDistance - getScrollX();
            }else{
                scrollX = (int) deltaX *-1;
            }
        }

        scrollBy(scrollX , 0);
    }

    /**
     * 通过viewHolder获得deleteView,若没有则返回find的结果
     * @return
     */
    private View getDeleteView(){
        try {
            SlideDeletionHolder holder = (SlideDeletionHolder) this.getTag(this.getId());
            return holder.deleteBtn;
        } catch (Exception e) {
            //可能没有绑定ViewHolder,则重新获取
            return this.findViewById(R.id.delete);
        }
    }

    /**
     * 滑出的最大长度，也就是deleteView的宽度
     * @return
     */
    public int getDeleteViewWidth(){
        int length = 0;
        final View view = getDeleteView();
        if (view != null){
            length =view.getWidth();
        }
        return length;
    }

    /**
     * 匀速收起，速度为 getDeleteViewWidth() / SCROLL_TIME ，即最大长度/最大滑动事件
     * @return
     */
    private int getShrinkDuration(){
        return (int) (getScrollX() *1.0f / getDeleteViewWidth() *SCROLL_TIME);
    }

    /**
     * 匀速展开，速度为 getDeleteViewWidth() / SCROLL_TIME ，即最大长度/最大滑动事件
     * @return
     */
    private int getStretchDuration(){
        return (int) ((1 -  getScrollX()*1.0 / getDeleteViewWidth()  )* SCROLL_TIME);
    }

    private void setIsSlide(boolean isSlide){
        try {
            SlideDeletionHolder holder = (SlideDeletionHolder) this.getTag(this.getId());
            holder.dataWrappeds.get(holder.position).isSlide = isSlide;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
