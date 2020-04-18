package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.joshua.myapplication.R;
import com.joshua.myapplication.view.CustomView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Joshua on 2020-4-18 11:32:16.
 */
public class TestCustomViewActivity extends AppCompatActivity {

    private static final String TAG = "TestCustomView";
    CustomView customView;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);
        customView = findViewById(R.id.custom_view);
        layoutParams = (LinearLayout.LayoutParams) customView.getLayoutParams();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lhf-" + TAG, "onStart() called");
        // 此时View还没测量完毕，获取的宽高都为0，可通过以下3种方式获取
        getCustomViewSize();

        // 获取View的宽高，方式一
        customView.post(new Runnable() {
            @Override
            public void run() {
                Log.d("lhf-" + TAG, "run() called");
                getCustomViewSize();
            }
        });

        // 获取View的宽高，方式二
        customView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("lhf-" + TAG, "onGlobalLayout() called");
                getCustomViewSize();
            }
        });
    }

    // 获取View的宽高，方式三
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d("lhf-" + TAG, "onWindowFocusChanged() called with: hasFocus = [" + hasFocus + "]");
        if (hasFocus) {
            getCustomViewSize();
        }
    }

    private void getCustomViewSize() {
        int width = customView.getMeasuredWidth();
        int height = customView.getMeasuredHeight();
        Log.d("lhf-" + TAG, "getCustomViewSize: width = [" + width + "], height = [" + height + "]");
    }

    public void doRequestLayout(View view) {
        Log.d("lhf-" + TAG, "doRequestLayout() called with: view = [" + view + "]");
        layoutParams.leftMargin = 100;
        // 调用后，会走父控件的onMeasure、onLayout，以及该View的onMeasure、onLayout、onDraw
        customView.requestLayout();
    }

    public void doInvalidate(View view) {
        Log.d("lhf-" + TAG, "doInvalidate() called with: view = [" + view + "]");
        Log.d("lhf-" + TAG, "doInvalidate: Thread.currentThread().getName() = [" + Thread.currentThread().getName() + "]");
        layoutParams.leftMargin = 200;
        // 调用后，只会走该View的onDraw
        customView.invalidate();
    }

    public void doPostInvalidate(View view) {
        Log.d("lhf-" + TAG, "doPostInvalidate() called with: view = [" + view + "]");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("lhf-" + TAG, "run: Thread.currentThread().getName() = [" + Thread.currentThread().getName() + "]");
                // TODO:@lhf run: android 10 上，在子线程更新UI竟然不报错？？？
                customView.setText("在子线程更新");
                // 作用同invalidate，可以在子线程调用
                customView.postInvalidate();
            }
        }).start();
    }
}
