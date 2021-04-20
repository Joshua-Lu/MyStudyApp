package com.joshua.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.joshua.myapplication.R;
import com.joshua.myapplication.service.FloatingButtonService;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * test Service
 * <p>
 * start方式启动，调用多次，只会走一次onCreate，但**每次会走onStartCommand**；
 * stop方式结束，只要**调用一次**，即可结束。
 * <p>
 * bind方式启动，调用多次，也只会走一次onCreate，并且**onBind也只会走一次**；
 * unbind方式结束，不同activity中bind过，就需要在那几个activity里**都掉unbind才行**
 * <p>
 * 混合方式启动，要结束需**所有activity调用unbind**，并且**调用一次stop**。
 * <p>
 * Created by Joshua on 2020-1-15 18:17:40.
 */
public class TestServiceActivity extends AppCompatActivity {

    private static final String TAG = "TestServiceActivity";

    public Intent service;
    public ServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_float_view);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected() called with: name = [" + name + "]");
            }
        };
        service = new Intent(TestServiceActivity.this, FloatingButtonService.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                startService(service);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startFloatingButtonService(View view) {
        if (FloatingButtonService.isStarted) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT).show();
            // 跳转到设置界面，请求授权
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            startService(service);
        }
    }

    public void stopService(View view) {
        stopService(service);
    }

    public void bindService(View view) {
        bindService(service, conn, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(conn);
    }
}
