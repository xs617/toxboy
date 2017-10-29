package com.wjr.toybox.widget.coordinatorLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjr.toybox.R;
import com.wjr.toybox.common.BaseFragmentPagerAdapter;
import com.wjr.toybox.common.FragmentBase;
import com.wjr.toybox.common.NumberTextRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/22.
 */

public class ViewPagerFragmentCoordinatorActivity extends AppCompatActivity{
    ViewPager viewPager;
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment_coordinatorlayout);
        for (int i=0;i<5;i++) {
            CoordinatorLayoutFragment baseFragment = new CoordinatorLayoutFragment();
            baseFragment.setMessage(""+i);
            fragments.add(baseFragment);
        }
//        this.getSupportFragmentManager().beginTransaction().add(R.id.layout,fragments.get(0)).commit();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragments));
    }


    public static class CoordinatorLayoutFragment extends FragmentBase{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root =  inflater.inflate(R.layout.fragment_coordinator_layout,container,false);
            RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i=0;i<20;i++){
                numbers.add(i);
            }
            recyclerView.setAdapter(new NumberTextRecyclerAdapter(getContext(),numbers));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            TextView textView = (TextView) root.findViewById(R.id.message);
            textView.setText(getMessage());
            return root;
        }
    }

}
