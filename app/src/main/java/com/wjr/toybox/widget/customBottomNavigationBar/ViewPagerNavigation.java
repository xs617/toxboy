package com.wjr.toybox.widget.customBottomNavigationBar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;

import com.wjr.toybox.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/13.
 */

public class ViewPagerNavigation implements View.OnClickListener ,IViewPagerNavigation{
    protected ArrayList<CompoundButton> mBaseTabs;
    private int tabSelectedTextColor;
    private int tabNoSelectTextColor;
    private int mCurPosition =-1;
    private static final double offsetCompleteRate = 0.8;

    public ViewPagerNavigation(Context context,@NonNull ArrayList<CompoundButton> tabBtns) {
        tabNoSelectTextColor = context.getResources().getColor(R.color.colorBottomTabNoSelect);
        tabSelectedTextColor = context.getResources().getColor(R.color.colorBottomTabSelected);

        this.mBaseTabs = tabBtns;
        for (CompoundButton compoundButton:tabBtns){
            if (compoundButton != null){
                compoundButton.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag(v.getId());
        if(position >= 0 && position < mBaseTabs.size()){
            setCurPosition(position);
        }
    }

    private int getColorInBoundary(float percent , int fromColor , int toColor){
        int fromR = Color.red(fromColor);
        int fromG = Color.green(fromColor);
        int fromB = Color.blue(fromColor);
        int toR = Color.red(toColor);
        int toG = Color.green(toColor);
        int toB = Color.blue(toColor);

        int rPercent  = (int) ((toR - fromR)*percent + fromR);
        int gPercent  = (int) ((toG - fromG)*percent + fromG);
        int bPercent  = (int) ((toB - fromB)*percent + fromB);
        return  Color.rgb(rPercent,gPercent,bPercent);
    }

    public int getCurPosition(){
        return mCurPosition;
    }

    private void setCurPosition(int target){
        if(mCurPosition != target && target >=0 && target < mBaseTabs.size()){
            //将上一个目标文字颜色改为未选中，如果是第一次调用，那么上一个目标位置可能为-1，跳过该步骤
            if (mCurPosition >= 0&& mCurPosition <mBaseTabs.size()) {
                mBaseTabs.get(mCurPosition).setChecked(false);
                mBaseTabs.get(mCurPosition).setTextColor(tabNoSelectTextColor);
            }
            mBaseTabs.get(target).setChecked(true);
            mBaseTabs.get(target).setTextColor(tabSelectedTextColor);
            mCurPosition = target;
        }else{
            mBaseTabs.get(target).setChecked(true);
        }
    }

    public int getTabSelectedTextColor() {
        return tabSelectedTextColor;
    }

    public void setTabSelectedTextColor(int tabSelectedTextColor) {
        this.tabSelectedTextColor = tabSelectedTextColor;
    }

    public int getTabNoSelectTextColor() {
        return tabNoSelectTextColor;
    }

    public void setTabNoSelectTextColor(int tabNoSelectTextColor) {
        this.tabNoSelectTextColor = tabNoSelectTextColor;
    }

    @Override
    public void toTargetPager(int target) {
        setCurPosition(target);
    }

    @Override
    public void scrollWithPager(int from, int to, float percent) {
        mBaseTabs.get(from).setTextColor(getColorInBoundary(percent, tabSelectedTextColor, tabNoSelectTextColor));
        mBaseTabs.get(to).setTextColor(getColorInBoundary(percent, tabNoSelectTextColor, tabSelectedTextColor));
        if(percent > offsetCompleteRate){
            setCurPosition(to);
        }
    }
}
