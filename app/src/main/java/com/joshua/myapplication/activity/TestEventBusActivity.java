package com.joshua.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.joshua.myapplication.R;
import com.joshua.myapplication.common.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试EventBus的使用，发送事件
 *
 * @author Joshua
 * @date 2021/4/18 17:03
 */
public class TestEventBusActivity extends AppCompatActivity {

    private static final String TAG = "TestEventBusActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void sendEvent(View view) {
        startActivity(new Intent(this, TestEventBusActivity2.class));

    }

    @Subscribe
    public void onGetEvent(MyEvent event) {
        Log.d(TAG, "onGetEvent() called with: event = [" + event + "]");
    }
}