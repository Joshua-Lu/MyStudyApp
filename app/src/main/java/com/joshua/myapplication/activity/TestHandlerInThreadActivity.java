package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.joshua.myapplication.R;
import com.joshua.myapplication.viewutils.SetOnClickListener;
import com.joshua.myapplication.viewutils.ViewUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 实现主线程通过Handler向子线程发送消息，一般开发中不建议这么做
 */
public class TestHandlerInThreadActivity extends AppCompatActivity {

    private static final String TAG = "TestHandlerInThread";

    Handler subHandler;
    public Looper looper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler_in_thread);
        ViewUtils.init(this);

        new Thread(() -> {
            Log.d(TAG, "onCreate: Thread start");
            // 在子线程创建Handler，必须先调该方法，否则在Handler的构造方法里，获取到Looper对象为空
            // 就抛出异常 RuntimeException: Can't create handler inside thread Thread[Thread-2,5,main] that has not called Looper.prepare()
            // 在主线程可以直接创建是因为在ActivityThread.main方法里有调 Looper.prepareMainLooper()
            Looper.prepare();
            looper = Looper.myLooper();

            subHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    Log.d(TAG, "handleMessage() called with: msg = [" + msg + "]");
                    Toast.makeText(TestHandlerInThreadActivity.this, "msg.what: " + msg.what, Toast.LENGTH_SHORT).show();
                }
            };

            // 调该方法才会不断循环去取Message
            Looper.loop();
            Log.d(TAG, "onCreate: Thread end");
        }).start();

    }

    @SetOnClickListener(R.id.button12)
    public void onClick() {
        Log.d(TAG, "onClick() called");
        subHandler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须在Activity退出时，调用looper.quit()，否则Looper.loop()一直在循环，Thread无法结束
        looper.quit();
    }
}