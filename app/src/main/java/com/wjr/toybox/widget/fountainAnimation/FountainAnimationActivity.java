package com.wjr.toybox.widget.fountainAnimation;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.wjr.toybox.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/6/12.
 */

public class FountainAnimationActivity extends Activity{
    ImageView icon;
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fountain_animation);
        icon = (ImageView) findViewById(R.id.icon);
        time = (TextView) findViewById(R.id.time);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(icon,"alpha",1f,0f,1f);
//        alphaAnimator.setRepeatCount(-1);
        alphaAnimator.setDuration(2000);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(icon,"rotation",0f,360f,0f);
//        rotationAnimator.setRepeatCount(-1);
        rotationAnimator.setDuration(2000);
        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(icon,"translationX",0f,200f,0f);
//        translationXAnimator.setRepeatCount(-1);
        translationXAnimator.setDuration(2000);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(icon,"scaleX",1f,1.5f,1f);
//        scaleAnimator.setRepeatCount(-1);
        scaleAnimator.setDuration(2000);

//        alphaAnimator.start();
//        rotationAnimator.start();
//        translationXAnimator.start();
//        scaleAnimator.start();

//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(translationXAnimator).with(alphaAnimator).before(scaleAnimator).with(rotationAnimator);
//        animatorSet.start();
//        animatorSet.setDuration(3000);

        ObjectAnimator xmlAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.animator_demo);
        xmlAnimator.setTarget(icon);
        xmlAnimator.start();

        String imageStr = "base64,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";
        int start = imageStr.indexOf(",");
        if (start != -1){
            imageStr = imageStr.substring(start,imageStr.length());
        }
         byte[] decodedString = Base64.decode(imageStr,Base64.DEFAULT);
         icon.setImageBitmap(BitmapFactory.decodeByteArray(decodedString,0,decodedString.length));

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
        String dateString = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        time.setText(dateString);

    }
}
