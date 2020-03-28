package com.joshua.myapplication.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Joshua on 2020/2/24 17:54.
 */
public class TimeReceiver extends BroadcastReceiver {

    private static final String TAG = "TimeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences sharedPreferences1 = context.getSharedPreferences(
                "alarm_record1", Activity.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(
                "alarm_record2", Activity.MODE_PRIVATE);
        String hour = String.valueOf(Calendar.getInstance().get(
                Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(Calendar.getInstance().get(
                Calendar.MINUTE));
        String time1 = sharedPreferences1.getString(hour + ":" + minute, null);
        String time2 = sharedPreferences2.getString(hour + ":" + minute, null);
        Log.d(TAG, "onReceive: time1 = [" + time1 + "]");
        Log.d(TAG, "onReceive: time2 = [" + time2 + "]");
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (time1 != null) {
            Log.d(TAG, "onReceive: 1111111111111 RINGER_MODE_SILENT");
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
        if (time2 != null) {
            Log.d(TAG, "onReceive:22222222222222 RINGER_MODE_NORMAL");
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

    }


}
