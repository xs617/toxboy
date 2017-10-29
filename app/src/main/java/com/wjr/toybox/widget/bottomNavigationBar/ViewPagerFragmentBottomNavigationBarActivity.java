package com.wjr.toybox.widget.bottomNavigationBar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjr.toybox.R;
import com.wjr.toybox.common.FragmentBase;
import com.wjr.toybox.common.NumberTextRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/22.
 */

public class ViewPagerFragmentBottomNavigationBarActivity extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment_coordinatorlayout);
        BottomNavigationView bottomNavigationBar = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setVisibility(View.VISIBLE);
        bottomNavigationBar.setOnNavigationItemSelectedListener(onItemSelectedListener);
        for (int i = 0; i < 5; i++) {
            CoordinatorLayoutFragment baseFragment = new CoordinatorLayoutFragment();
            baseFragment.setMessage("" + i);
            fragments.add(baseFragment);
        }
//        this.getSupportFragmentManager().beginTransaction().add(R.id.layout,fragments.get(0)).commit();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CoordinatorFragmentPagerAdapter(getSupportFragmentManager(), fragments));
    }
    private BottomNavigationView.OnNavigationItemSelectedListener onItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int index = -1;
            switch (item.getItemId()){
                case R.id.homePage:
                    index = 0;
                    break;
                case R.id.planCenter:
                    index = 1;
                    break;
                case R.id.myPlan:
                    index = 2;
                    break;
                case R.id.aboutMe:
                    index =3;
                    break;
            }
            if (index != -1) {
                viewPager.setCurrentItem(index);
                return true;
            }
            return false;
        }
    };


    private class CoordinatorFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments;

        public CoordinatorFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    public static class CoordinatorLayoutFragment extends FragmentBase {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_coordinator_layout, container, false);
            RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            for (int i = 0; i < 20; i++) {
                numbers.add(i);
            }
            recyclerView.setAdapter(new NumberTextRecyclerAdapter(getContext(), numbers));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            TextView textView = (TextView) root.findViewById(R.id.message);
            textView.setText(getMessage());
            return root;
        }
    }

}
