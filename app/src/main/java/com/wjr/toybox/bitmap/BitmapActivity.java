package com.wjr.toybox.bitmap;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.wjr.toybox.R;

public class BitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        ImageView imageView = (ImageView) findViewById(R.id.show);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.mipmap.dog,options);

        DisplayMetrics displayMetrics =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        int width = (int) (displayMetrics.density  * 300);
        int height = (int) (displayMetrics.density * 400);
        options.inSampleSize = Math.max(options.outHeight / height,options.outWidth / width);

        options.inJustDecodeBounds = false;
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.dog,options));


    }
}
