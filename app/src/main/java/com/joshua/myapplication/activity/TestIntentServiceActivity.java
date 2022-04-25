package com.joshua.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joshua.myapplication.R;
import com.joshua.myapplication.service.MyIntentService;

import androidx.appcompat.app.AppCompatActivity;

public class TestIntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent_service);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.setAction(MyIntentService.ACTION_BAZ);
        intent.setAction(MyIntentService.ACTION_FOO);
        intent.putExtra(MyIntentService.EXTRA_PARAM1, "hello");
        intent.putExtra(MyIntentService.EXTRA_PARAM2, "world");
        startService(intent);
    }
}