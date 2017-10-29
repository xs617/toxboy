package com.wjr.toybox.effect.slideDeleteAbstract;

/**
 * Created by Administrator on 2017/2/24.
 */

/**
 * 基本的数据类型，记录了是否滑出，继承该类并扩展数据类
 */
public class BaseData {
    private boolean isSlide;

    public BaseData() {
        this.isSlide = false;
    }

    public void setIsSlide(boolean isSlide){
        this.isSlide = isSlide;
    }

    public boolean getIsSlide(){
        return isSlide;
    }
}
