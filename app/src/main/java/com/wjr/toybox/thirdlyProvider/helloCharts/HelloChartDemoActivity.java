package com.wjr.toybox.thirdlyProvider.helloCharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wjr.toybox.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import until.TimeUtil;

/**
 * Created by Administrator on 2017/4/8.
 */

public class HelloChartDemoActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_charts);

        ColumnChartView columnChartView = (ColumnChartView) findViewById(R.id.column_chart_view);
        int numSubcolumns = 1;
        int numColumns = 7;
        boolean hasLabels = true;
        boolean hasLabelForSelected = false;
        boolean hasAxes = true;
        ArrayList<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColumns; ++i) {

            values = new ArrayList<SubcolumnValue>();
            for (int j = 0; j < numSubcolumns; ++j) {
                SubcolumnValue subcolumnValue = new SubcolumnValue();
                long time = (i%3 +1) * 40 * 60 ;
                subcolumnValue.setValue(time);
                String timeString = TimeUtil.getTimeHour(time *1000)+ "时" + TimeUtil.getTimeMinute(time*1000) + "分";
                subcolumnValue.setLabel(timeString);
                subcolumnValue.setColor( ChartUtils.pickColor());
                values.add(subcolumnValue);
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(true);
            columns.add(column);
        }
        ColumnChartData data = new ColumnChartData(columns);
        data.setFillRatio(0.5f);
        data.setValueLabelBackgroundAuto(false);
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);
        if (hasAxes) {
            Axis axisX = new Axis().setTextColor(Color.WHITE);
            ArrayList<AxisValue> axisValueArrayList = new ArrayList<AxisValue>();
            for (int i=0;i<columns.size();i++) {
                AxisValue axisValue = new AxisValue(i);
                axisValue.setLabel(10 +i +"");
                axisValueArrayList.add(axisValue);
                axisX.setValues(axisValueArrayList);
            }
//            Axis axisY = new Axis().setHasLines(true);
//            if (hasAxesNames) {
//                axisX.setName("本周");
//                axisY.setName("累计学习时长");
//            }
            data.setAxisXBottom(axisX);
//            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
//        columnChartView.setContainerScrollEnabled(false, ContainerScrollType.HORIZONTAL);
//        columnChartView.setContainerScrollEnabled(false, ContainerScrollType.VERTICAL);
//        columnChartView.setInteractive(false);
        columnChartView.setZoomEnabled(false);
        columnChartView.setColumnChartData(data);
    }
}
