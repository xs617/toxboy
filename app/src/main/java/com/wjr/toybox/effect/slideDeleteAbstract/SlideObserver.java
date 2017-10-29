package com.wjr.toybox.effect.slideDeleteAbstract;

/**
 * Created by Administrator on 2017/2/27.
 */

/**
 * 观察deleteView滑出、收起
 */
public interface SlideObserver {
    /**
     * 反馈deleteView滑出、收起
     * @param position 滑动的item在list中的位置，与data位置对应
     * @param isSlide  是否被滑出，true为滑出，false为收起
     */
    void notifySlideChange(int position,boolean isSlide);
}
