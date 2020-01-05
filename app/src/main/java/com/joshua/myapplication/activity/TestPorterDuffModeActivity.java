package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.view.View;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试Paint相关属性
 * <p>
 * Created by Joshua on 2019/12/29 .
 */
public class TestPorterDuffModeActivity extends AppCompatActivity {

    View allModeView;
    View clearModeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_paint);

        allModeView = findViewById(R.id.view_all);
        clearModeView = findViewById(R.id.view_clear);

    }

    public void showAllMode(View view) {
        allModeView.setVisibility(View.VISIBLE);
        clearModeView.setVisibility(View.GONE);
    }

    public void showClearMode(View view) {
        allModeView.setVisibility(View.GONE);
        clearModeView.setVisibility(View.VISIBLE);
    }
}
