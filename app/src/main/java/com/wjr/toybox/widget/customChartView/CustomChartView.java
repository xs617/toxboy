package com.wjr.toybox.widget.customChartView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/27.
 */

public class CustomChartView extends View{
    ArrayList<ChartPoint> points = new ArrayList<ChartPoint>();
    public CustomChartView(Context context) {
        this(context,null,0);
    }

    public CustomChartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPointSet(ArrayList<ChartPoint> pointSet,boolean isAppend){
        if (!isAppend) {
            this.points.clear();
        }
        this.points.addAll(pointSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        int xInterval = width / (points.size() - 1);


    }


    class ChartPoint {
        public int x;
        public float y;
    }
}
