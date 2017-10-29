package com.wjr.toybox.effect.drawerLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/3/9.
 */

public class DemoDrawerLayout extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout_demo);
        ListView listView = (ListView) findViewById(R.id.drawer);
        final TextView textView = (TextView) findViewById(R.id.content);
        final ListAdapter listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{
                "1","2","3"
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText((String)listAdapter.getItem(position));
            }
        });
        listView.setAdapter(listAdapter);

    }
}
