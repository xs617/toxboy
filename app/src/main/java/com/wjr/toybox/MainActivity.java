package com.wjr.toybox;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> redirectDesDatas = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.toy_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < redirectDesDatas.size()) {
                    Intent intent = new Intent();
//                    intent.setClassName(MainActivity.this, redirectDesDatas.get(position));
                    intent.setClassName(getApplication(), redirectDesDatas.get(position));
                    startActivity(intent);
                    Log.e("launchMode", " lastTaskId :" + MainActivity.this.getTaskId());
                }
            }
        });

        TypedArray toysNameArray = getResources().obtainTypedArray(R.array.toys_name);
        int nameLength = toysNameArray.length();
        for (int i = 0; i < nameLength; i++) {
            arrayAdapter.add(toysNameArray.getString(i));
        }
        toysNameArray.recycle();

        TypedArray redirectArray = getResources().obtainTypedArray(R.array.toys_activity);
        int redirectLength = redirectArray.length();
        for (int i = 0; i < redirectLength; i++) {
            redirectDesDatas.add(redirectArray.getString(i));
        }
        redirectArray.recycle();

        Log.e("launchMode", " cur TaskId :" + this.getTaskId());

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
