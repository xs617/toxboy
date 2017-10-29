package com.wjr.toybox.widget.customEmojiEditTextView;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

import com.wjr.toybox.R;

/**
 * Created by Administrator on 2017/6/6.
 */

public class CustomEmojiEditTextActivity extends Activity {
    EditText editText;
    View face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_emoji_edit_text);
        face = findViewById(R.id.face);
        editText = (EditText) findViewById(R.id.custom_emoji);
        ImageSpan imageSpan = new ImageSpan(this,R.mipmap.ic_launcher);
        SpannableString spannableString = new SpannableString("100");
        spannableString.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setText(spannableString);
    }
}
