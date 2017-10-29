package com.wjr.toybox.effect.slideDeletion;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.wjr.toybox.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/20.
 */

public class SlideDeletionActivity extends Activity {
    SlideDeletionAdapter slideDeletionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_deletion);
        ListView listView = (ListView) findViewById(R.id.list);
        final ArrayList<DataWrapped> data = new ArrayList<DataWrapped>();
        slideDeletionAdapter = new SlideDeletionAdapter(this,data);
        slideDeletionAdapter.setItemOperateListener(new SlideDeletionAdapter.ItemOperateListener() {
            @Override
            public void OnDelete(DataWrapped dataWrapped) {
                Toast.makeText(SlideDeletionActivity.this,"item "+ dataWrapped.getMessage() +"delete",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnClick(DataWrapped dataWrapped) {
                Toast.makeText(SlideDeletionActivity.this,"item "+ dataWrapped.getMessage() +" click",Toast.LENGTH_SHORT).show();
            }
        });
        for (int i = 0; i < 50; i++) {
            data.add(new DataWrapped(""+i));
        }
        listView.setAdapter(slideDeletionAdapter);
    }
}