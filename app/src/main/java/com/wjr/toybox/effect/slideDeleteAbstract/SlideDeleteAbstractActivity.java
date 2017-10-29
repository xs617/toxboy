package com.wjr.toybox.effect.slideDeleteAbstract;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wjr.toybox.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23.
 */

public class SlideDeleteAbstractActivity extends Activity{
    SlideDeletionBaseAdapter slideDeletionAdapter;
    final ArrayList<BaseData> data = new ArrayList<BaseData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_deletion);
        ListView listView = (ListView) findViewById(R.id.list);
        slideDeletionAdapter = new SlideDeletionBaseAdapter(this,data){

            @Override
            protected BaseHolder onCreateCustomHolder(Context context, ViewGroup parentLayout) {
                View customView = LayoutInflater.from(context).inflate(R.layout.item_slide_deletion_custom_content,parentLayout,true);
                final ExampleHolder exampleHolder = new ExampleHolder();
                exampleHolder.checkBox = (CheckBox) customView.findViewById(R.id.checkbox);
                exampleHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ExampleData datas = (ExampleData) dataList.get(exampleHolder.position);
                        datas.isCheck = !datas.isCheck;
                    }
                });
                exampleHolder.message = (TextView) customView.findViewById(R.id.message);
                return exampleHolder;
            }

            @Override
            protected void onBindViewWithData(BaseHolder holder, BaseData data) {
                ExampleHolder exampleHolder;
                try {
                    ExampleData exampleData = (ExampleData) data;
                    exampleHolder = (ExampleHolder) holder;
                    exampleHolder.checkBox.setChecked(exampleData.isCheck);
                    exampleHolder.message.setText(exampleData.message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        slideDeletionAdapter.setItemOperateListener(new ItemOperateListener() {
            @Override
            public boolean OnDeletePrepare(BaseData data) {
                Toast.makeText(SlideDeleteAbstractActivity.this,"item " +"delete",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void OnDeleteFinish(BaseData baseData, boolean isDeleteSuccess) {

            }


            @Override
            public void OnClick(BaseData dataWrapped) {
                Toast.makeText(SlideDeleteAbstractActivity.this,"item "+" click",Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < 50; i++) {
            data.add(new ExampleData(i+"",false));
        }
        listView.setAdapter(slideDeletionAdapter);
    }
}
