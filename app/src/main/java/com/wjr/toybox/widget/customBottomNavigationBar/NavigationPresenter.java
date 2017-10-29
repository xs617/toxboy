package com.wjr.toybox.widget.customBottomNavigationBar;

import android.support.v4.view.ViewPager;

/**
 * Created by Administrator on 2017/3/23.
 */

public class NavigationPresenter implements ViewPager.OnPageChangeListener{
    IViewPagerNavigation iViewPagerNavigationImp;
    public NavigationPresenter() {

    }

    public NavigationPresenter setiViewPagerNavigationImp(IViewPagerNavigation iViewPagerNavigationImp){
        this.iViewPagerNavigationImp = iViewPagerNavigationImp;
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
