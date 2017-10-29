package com.wjr.toybox.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/1/13.
 */

public class FragmentBase extends Fragment {
    private String message = null;
    protected String LIFE_CYCLE_TAG = "lifeCycleTag";
    private View rootView = null;
    private TextView messageView = null;
    private boolean isLoadAfterCreateView = false;
    protected boolean isNeedLoad = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LIFE_CYCLE_TAG, "onCreate " + message);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(LIFE_CYCLE_TAG, "onCreateView " + message);
        rootView = inflater.inflate(R.layout.fragment_base, container, false);

        messageView = (TextView) rootView.findViewById(R.id.message);
        if (messageView != null && message != null) {
            messageView.setText(message);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isLoadAfterCreateView && isParentFragmentVisible() && isNeedLoad) {
            onLazyLoadData();
            isNeedLoad = false;
            isLoadAfterCreateView = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(LIFE_CYCLE_TAG, "onStart " + message);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(LIFE_CYCLE_TAG, "onResume " + message);
        Log.e(LIFE_CYCLE_TAG, "userVisible :" + getUserVisibleHint() + " when resume " + message);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(LIFE_CYCLE_TAG, "onPause " + message);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(LIFE_CYCLE_TAG, "onStop " + message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LIFE_CYCLE_TAG, "onDestroy " + message);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(LIFE_CYCLE_TAG, "userVisible :" + isVisibleToUser + "  :" + message);
        if (isVisibleToUser) {
            if (getView() == null) {
                isLoadAfterCreateView = true;
                return;
            } else {
                isLoadAfterCreateView = false;
            }
            if (isParentFragmentVisible() && isNeedLoad) {
                onLazyLoadData();
                isNeedLoad = false;
            }
            if (onFragmentVisibleListener != null){
                onFragmentVisibleListener.onFragmentVisible(this);
            }
        }
    }

    /**
     * 判断parentFragment的userVisible是否为true
     *
     * @return 如果没有parentFragment或者parentFragment的userVisible为true，则返回true，否则返回false
     */
    private boolean isParentFragmentVisible() {
        Fragment parent = getParentFragment();
        if (parent != null) {
            boolean isParentVisible = parent.getUserVisibleHint();
            if (!isParentVisible) {
                setUserVisibleHint(false);
                return false;
            }
        }
        return true;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected void onLazyLoadData() {
        Log.e(LIFE_CYCLE_TAG, "onLazyLoad :" + "userVisible :" + getUserVisibleHint() + " " + message);
    }

    OnFragmentVisibleListener onFragmentVisibleListener;

    public void setOnFragmentVisibleListener(OnFragmentVisibleListener onFragmentVisibleListener) {
        this.onFragmentVisibleListener = onFragmentVisibleListener;
    }

    public interface OnFragmentVisibleListener {
        void onFragmentVisible(FragmentBase fragment);
    }



}
