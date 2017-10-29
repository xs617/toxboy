package com.wjr.toybox.widget.customBottomNavigationBar;

/**
 * Created by Administrator on 2017/3/23.
 */

public interface IViewPagerNavigation {
    void toTargetPager(int target);
    void scrollWithPager(int from, int to, float percent);
}
