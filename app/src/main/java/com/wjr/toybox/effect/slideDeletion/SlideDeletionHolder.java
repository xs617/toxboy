package com.wjr.toybox.effect.slideDeletion;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/22.
 */

public class SlideDeletionHolder {
    TextView messageView;
    TextView deleteBtn;
    int position;
    ArrayList<DataWrapped> dataWrappeds;

    public SlideDeletionHolder(ArrayList<DataWrapped> dataWrappeds) {
        this.dataWrappeds = dataWrappeds;
    }
}
