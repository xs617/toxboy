package com.wjr.toybox.widget.viewPager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wjr.toybox.R;
import com.wjr.toybox.common.BaseFragmentPagerAdapter;
import com.wjr.toybox.common.FragmentBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/15.
 * 多层嵌套的懒加载的Fragment
 * viewpager
 *      fragmentBase
 *      fragmentComp
 *          viewpager
 *              fragmentBase
 *              fragmentBase
 *      fragmentBase
 */

public class ViewPagerFragmentActivity extends android.support.v4.app.FragmentActivity implements ViewPager.OnPageChangeListener, OnTabChangeListener, View.OnClickListener {
    View mBottomTabView;
    BaseTab mBottomTab;
    ArrayList<Fragment> mFragments;
    BaseFragmentPagerAdapter mMainFragmentAdapter;
    ViewPager mHomeViewPager;
    float lastOffset = 0;
    public static final String RESTORE_STATE_KEY = "restoreStateKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment);
        findViewById(R.id.redirectChapter).setOnClickListener(this);
        findViewById(R.id.redirectVideo).setOnClickListener(this);

        mBottomTabView = findViewById(R.id.bottom_bar);
        mBottomTab = new BaseTab(this,mBottomTabView,R.array.main_bottom_tab_ids);
        mBottomTab.setOnTabChangeListener(this);

        mFragments = new ArrayList<Fragment>();
        for (int i = 0; i < 4; i++) {
            FragmentBase fragmentBase;
            if (i == 2) {
                fragmentBase = new FragmentComp();
            }else{
                fragmentBase = new FragmentBase();
            }
            fragmentBase.setMessage("outer "+ i);
            mFragments.add(fragmentBase);

        }

        mHomeViewPager = (ViewPager) findViewById(R.id.home_view_pager);
        mMainFragmentAdapter = new BaseFragmentPagerAdapter(this.getSupportFragmentManager(), mFragments);
        mHomeViewPager.setAdapter(mMainFragmentAdapter);
        mHomeViewPager.setOffscreenPageLimit(3);
        mHomeViewPager.addOnPageChangeListener(this);

        redirectByAction(null);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        redirectByAction(null);
    }

    private void redirectByAction(Bundle savedInstanceState) {
        String action = this.getIntent().getAction();
        if (action == null) {
            int lastPosition;
            if (savedInstanceState == null || (lastPosition = savedInstanceState.getInt(RESTORE_STATE_KEY)) == 0) {
                mBottomTab.setTargetTab(0);
            } else {
                //恢复进程被杀死时的状态
                mBottomTab.setTargetTab(lastPosition);
            }
            return;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RESTORE_STATE_KEY, mHomeViewPager.getCurrentItem());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.e("pageScroll", "onPageScrolled: positionOffset" + positionOffset +" pixels " + positionOffsetPixels +" position "+ position+ " lightPosition "+ lightPosition);
        if (positionOffset == 0) {
            lastOffset = 0;
            return;
        }
        if (positionOffset - lastOffset > 0) {
            if (mBottomTab.getCurTab() == position) {
                mBottomTab.changeTabWithScroll(position, position + 1, positionOffset);
            } else if (mBottomTab.getCurTab() == position + 1) {
                mBottomTab.changeTabWithScroll(position + 1, position, 1 - positionOffset);
            }
        }
        if (positionOffset - lastOffset < 0) {
            if (mBottomTab.getCurTab() == position + 1) {
                mBottomTab.changeTabWithScroll(position + 1, position, 1 - positionOffset);
            } else if (mBottomTab.getCurTab() == position) {
                mBottomTab.changeTabWithScroll(position, position + 1, positionOffset);
            }
        }
        lastOffset = positionOffset;
    }


    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChange(int from, int to) {
        mHomeViewPager.setCurrentItem(to, false);
    }

    public void showBottomTab() {
        mBottomTabView.setVisibility(View.VISIBLE);
    }

    public void hideBottomTab() {
        mBottomTabView.setVisibility(View.GONE);
    }


    public void setPager(int position) {
        if (position >= 0 && position < mFragments.size()) {
            mBottomTab.setTargetTab(position);
        }
    }

    @Override
    public void onClick(View v) {
    }
}
