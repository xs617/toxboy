package com.wjr.toybox.common.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/3/23.
 */

public class CustomBottomNavigation {
    public CustomBottomNavigation(Context context) {

    }

    private void inflateView(Context context, ViewGroup viewGroup){
        LayoutInflater.from(context).inflate(R.layout.custom_bottom_navigation,viewGroup,false);

    }

}
