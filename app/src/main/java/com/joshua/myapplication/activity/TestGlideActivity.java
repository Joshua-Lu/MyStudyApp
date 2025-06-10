package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joshua.myapplication.R;
import com.joshua.myapplication.utils.Constants;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试Glide使用
 *
 * @author Joshua
 * @date 2021/4/6 21:32
 */
public class TestGlideActivity extends AppCompatActivity {

    ImageView ivGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);

        ivGlide = findViewById(R.id.iv_glide);
//        Glide.with(this).load(Constants.imageUrl).into(ivGlide);
        Glide.with(this).load(Constants.gifUrl).into(ivGlide);
    }
}