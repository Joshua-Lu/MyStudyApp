package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.util.Log;

import com.joshua.myapplication.R;
import com.joshua.myapplication.common.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试EventBus的使用，接收事件
 *
 * @author Joshua
 * @date 2021/4/18 17:03
 */
public class TestEventBusActivity2 extends AppCompatActivity {

    private static final String TAG = "TestEventBusActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus2);
        EventBus.getDefault().post(new MyEvent("My First Event"));
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onGetEvent(MyEvent event) {
        Log.d(TAG, "onGetEvent() called with: event = [" + event + "]");
    }
}