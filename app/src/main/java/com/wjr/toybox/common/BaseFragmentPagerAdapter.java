package com.wjr.toybox.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/15.
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter{
    ArrayList<Fragment> fragments;
    public BaseFragmentPagerAdapter(FragmentManager fm ,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        if (this.fragments == null){
            this.fragments = new ArrayList<Fragment>();
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() >= 0 && position < fragments.size() ){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null){
            return  fragments.size();
        }
        return 0;
    }

    @Override
    public int getItemPosition(Object object) {
        for (int i=0;i<fragments.size();i++) {
            if (object == fragments.get(i)){
                return i;
            }
        }
        return -1;
    }
}
