package com.wjr.toybox.widget.viewPager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import com.wjr.toybox.R;
import com.wjr.toybox.common.BaseFragmentPagerAdapter;
import com.wjr.toybox.common.FragmentBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/13.
 */

public class FragmentComp extends FragmentBase implements View.OnClickListener {
    ViewPager viewPager = null;
    ArrayList<Fragment> innerFragments = new ArrayList<Fragment>();
    BaseFragmentPagerAdapter baseFragmentPagerAdapter;
    static final int count = 2;
    ToggleButton left;
    ToggleButton right;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager_setting_viewpager_fragment, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        for (int i = 0; i < count; i++) {
            FragmentBase fragmentBase = new FragmentBase();
            fragmentBase.setOnFragmentVisibleListener(new OnFragmentVisibleListener() {
                @Override
                public void onFragmentVisible(FragmentBase fragment) {
                    Log.e(LIFE_CYCLE_TAG,"onFragmentVisible :" + getUserVisibleHint() + " " + fragment.getMessage());
                    innerPagerChange(baseFragmentPagerAdapter.getItemPosition(fragment));
                }
            });
            fragmentBase.setMessage("inner" + i);
            innerFragments.add(fragmentBase);
        }
        //注意这里要用getChildFragmentManager嵌套的fragment才能通过getParentFragment获取外层fragment
        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(this.getChildFragmentManager(), innerFragments);
        viewPager.setAdapter(baseFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        left = (ToggleButton) rootView.findViewById(R.id.left);
        right = (ToggleButton) rootView.findViewById(R.id.right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                innerPagerChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == left) {
            innerPagerChange(0);
//            viewPager.setCurrentItem(0);
        } else if (v == right) {
            innerPagerChange(1);
//            viewPager.setCurrentItem(1);
        }
    }

    private void innerPagerChange(int position){
        switch (position){
            case 0:
                viewPager.setCurrentItem(0);
                left.setChecked(true);
                right.setChecked(false);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                left.setChecked(false);
                right.setChecked(true);
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (innerFragments == null){
            return;
        }
        if (isVisibleToUser && viewPager != null && baseFragmentPagerAdapter != null){
            for (int i=0;i<innerFragments.size();i++){
                Fragment fragment = innerFragments.get(i);
                if (fragment == null){
                    continue;
                }
                if (i == viewPager.getCurrentItem()){
                    fragment.setUserVisibleHint(true);
                    return;
                }
                fragment.setUserVisibleHint(false);
            }
        }else{
            for (Fragment fragment :innerFragments){
                if (fragment != null) {
                    fragment.setUserVisibleHint(false);
                }
            }
        }
    }
}
