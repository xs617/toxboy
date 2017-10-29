package com.wjr.toybox.widget.customBottomNavigationBar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;

import com.wjr.toybox.R;
import com.wjr.toybox.common.BaseFragmentPagerAdapter;
import com.wjr.toybox.widget.coordinatorLayout.ViewPagerFragmentCoordinatorActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ViewPagerFragmentCustomBottomNavigationActivity extends AppCompatActivity{
    float lastOffset =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment_custom_navigation_bar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        for (int i=0;i<5;i++) {
            ViewPagerFragmentCoordinatorActivity.CoordinatorLayoutFragment baseFragment = new ViewPagerFragmentCoordinatorActivity.CoordinatorLayoutFragment();
            baseFragment.setMessage(""+i);
            fragments.add(baseFragment);
        }
        viewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragments));
        final int[] navigationTab = {R.id.home_page,R.id.plans_centre,R.id.my_plans,R.id.about_me};
        ArrayList<CompoundButton> compoundButtons = new ArrayList<CompoundButton>();
        for(int i=0;i<navigationTab.length;i++){
            CompoundButton tab = (CompoundButton) findViewById(navigationTab[i]);
            tab.setTag(tab.getId(),i);
            compoundButtons.add(i,tab);
        }
        ViewPagerNavigation viewPagerNavigation = new ViewPagerNavigation(this, compoundButtons);

        NavigationPresenter navigationPresenter = new NavigationPresenter();
        navigationPresenter.setiViewPagerNavigationImp(viewPagerNavigation);
        viewPager.addOnPageChangeListener(navigationPresenter);
    }
}
