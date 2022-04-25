package com.joshua.myapplication.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.joshua.myapplication.R;
import com.joshua.myapplication.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.joshua.myapplication.service.action.FOO";
    public static final String ACTION_BAZ = "com.joshua.myapplication.service.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.joshua.myapplication.service.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.joshua.myapplication.service.extra.PARAM2";

    private static final String TAG = "MyIntentService";
    public static final int ID = 22;
    private static final String IMPORTANT_CHANNEL_ID = "IMPORTANT_CHANNEL_ID";
    private static final String NORMAL_CHANNEL_ID = "NORMAL_CHANNEL_ID";

    Handler handler;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        Log.d(TAG, "handleActionFoo() called with: param1 = [" + param1 + "], param2 = [" + param2 + "]");
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MyIntentService.this, param1 + param2, Toast.LENGTH_SHORT).show();
//            }
//        });
        Intent intent = new Intent(this, MainActivity.class);
        // 创建PendingIntent方式1
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        // 创建PendingIntent方式2
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setNotificationChannel();
            notification = new Notification.Builder(this, IMPORTANT_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(param1)
                    .setContentText(param2)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        } else {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(param1)
                    .setContentText(param2)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
        }
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(ID, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setNotificationChannel() {
        NotificationChannel channel1 = new NotificationChannel(NORMAL_CHANNEL_ID,
                "普通通知", NotificationManager.IMPORTANCE_LOW);
        channel1.setDescription("这是普通通知，不太重要");

        NotificationChannel channel2 = new NotificationChannel(IMPORTANT_CHANNEL_ID,
                "重要通知", NotificationManager.IMPORTANCE_HIGH);
        channel2.setDescription("这是重要通知，建议开启");

        // Android O之后，通知相关的设置都是通过NotificationChannel来设置了
        // 设置通知出现时声音，默认通知是有声音的
        channel2.setSound(null, null);
        // 设置通知出现时的闪灯（如果 android 设备支持的话）
        channel2.enableLights(true);
        channel2.setLightColor(Color.RED);
        // 设置通知出现时的震动（如果 android 设备支持的话）
        channel2.enableVibration(true);
        channel2.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        List<NotificationChannel> channels = new ArrayList<>();
        channels.add(channel1);
        channels.add(channel2);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannels(channels);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        Log.d(TAG, "handleActionBaz() called with: param1 = [" + param1 + "], param2 = [" + param2 + "]");
    }
}
