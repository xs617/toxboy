package com.wjr.toybox.widget.viewPager.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.View;
import android.widget.ToggleButton;

import com.wjr.toybox.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/13.
 */

public class BaseTab implements View.OnClickListener{
    protected ArrayList<ToggleButton> mBaseTabs;
    private int tabSelectedTextColor;
    private int tabNoSelectTextColor;
    private int mCurTab =-1;
    private static final double offsetCompleteRate = 0.8;
    private OnTabChangeListener mOnTabChangeListener;

    public BaseTab(Context context, View rootView, int textViewResIdArray) {
        tabNoSelectTextColor = context.getResources().getColor(R.color.colorBottomTabNoSelect);
        tabSelectedTextColor = context.getResources().getColor(R.color.colorBottomTabSelectedText);


        mBaseTabs = new ArrayList<ToggleButton>();
        TypedArray typedArray = context.getResources().obtainTypedArray(textViewResIdArray);
        int tabNum = typedArray.length();
        for(int i=0;i<tabNum;i++){
            ToggleButton tab = (ToggleButton) rootView.findViewById(typedArray.getResourceId(i,0));
            tab.setTag(tab.getId(),i);
            tab.setOnClickListener(this);
            mBaseTabs.add(i,tab);
        }
        typedArray.recycle();

    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag(v.getId());
        if(position >= 0 && position < mBaseTabs.size()){
            setTargetTab(position);
        }
    }

    public void setOnTabChangeListener(OnTabChangeListener onTabChangeListener){
        this.mOnTabChangeListener = onTabChangeListener;
    }

   public void changeTabWithScroll(int from, int to, float percent){

        mBaseTabs.get(from).setTextColor(getColorInBoundary(percent, tabSelectedTextColor, tabNoSelectTextColor));
        mBaseTabs.get(to).setTextColor(getColorInBoundary(percent, tabNoSelectTextColor, tabSelectedTextColor));
        if(percent > offsetCompleteRate){
            setTargetTab(to);
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

    public int getCurTab(){
        return mCurTab;
    }

    public void setTargetTab(int targetTab){
        if(mCurTab != targetTab && mBaseTabs.size() > targetTab && targetTab >=0){
            //将上一个目标文字颜色改为未选中，如果是第一次调用，那么上一个目标位置可能为-1，跳过该步骤
            if (mCurTab >= 0&& mCurTab <mBaseTabs.size()) {
                mBaseTabs.get(mCurTab).setChecked(false);
                mBaseTabs.get(mCurTab).setTextColor(tabNoSelectTextColor);
            }
            mBaseTabs.get(targetTab).setChecked(true);
            mBaseTabs.get(targetTab).setTextColor(tabSelectedTextColor);
            if(mOnTabChangeListener != null){
                mOnTabChangeListener.onTabChange(mCurTab,targetTab);
            }
            mCurTab = targetTab;
        }else{
            mBaseTabs.get(targetTab).setChecked(true);
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
}
