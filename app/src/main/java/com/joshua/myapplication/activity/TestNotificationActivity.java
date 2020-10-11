package com.joshua.myapplication.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class TestNotificationActivity extends AppCompatActivity {

    private final String CHANNEL_ID = "1";
    private final int NOTIFICATION_ID_BASIC = 1;
    private final int NOTIFICATION_ID_COLLAPSE = 2;
    private final int NOTIFICATION_ID_HEADSUP = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);
    }

    private NotificationManager createNotificationManager() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 【Android O】 之后，需要设置NotificationChannel才行
        // 否则会提示 fail to post notification on channel null ，无法成功发送通知
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "testChannel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        return manager;
    }

    private Notification.Builder createBuilder(PendingIntent pendingIntent, int type) {
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID);
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.myicon))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setSubText("SubText");
        switch (type) {
            case NOTIFICATION_ID_BASIC:
                // 普通
                builder.setContentTitle("普通通知");
                break;
            case NOTIFICATION_ID_COLLAPSE:
                // 折叠式
                // 在首行或者通过手动拉开才能展示bigContentView的全部内容
                builder.setContentTitle("折叠式通知");
                // 自定义折叠状态界面
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_collapse);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    builder.setCustomContentView(contentView);
                } else {
                    builder.build().contentView = contentView;
                }
                // 自定义展开状态界面
                RemoteViews bigContentView = new RemoteViews(getPackageName(), R.layout.notification_expand);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    builder.setCustomBigContentView(bigContentView);
                } else {
                    builder.build().bigContentView = bigContentView;
                }
                break;
            case NOTIFICATION_ID_HEADSUP:
                // 悬挂式
                builder.setContentTitle("悬挂式通知");
                /* Apps targeting {@link Build.VERSION_CODES#Q} and above will have to request
                 * a permission ({@link android.Manifest.permission#USE_FULL_SCREEN_INTENT}) in order to
                 * use full screen intents.*/
                builder.setFullScreenIntent(pendingIntent, true);
                break;
            default:
                break;
        }
        return builder;
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    /**
     * 发送通知
     *
     * @param type
     */
    public void sendNotification(int type) {
        // 1.创建 PendingIntent
        PendingIntent pendingIntent = createPendingIntent();
        // 2.创建 Notification.Builder
        Notification.Builder builder = createBuilder(pendingIntent, type);
        // 3.获取NotificationManager
        NotificationManager manager = createNotificationManager();
        // 4.调用NotificationManager的notify方法
        manager.notify(type, builder.build());
    }

    /**
     * 普通通知
     *
     * @param view
     */
    public void sendBasicNotification(View view) {
        sendNotification(NOTIFICATION_ID_BASIC);
    }

    /**
     * 折叠式通知
     *
     * @param view
     */
    public void sendCollapseNotification(View view) {
        sendNotification(NOTIFICATION_ID_COLLAPSE);
    }

    /**
     * 悬挂式通知
     *
     * @param view
     */
    public void sendHeadsUpNotification(View view) {
        sendNotification(NOTIFICATION_ID_HEADSUP);
    }
}