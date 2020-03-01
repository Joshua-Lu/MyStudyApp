package com.joshua.myapplication.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * add SystemInfoActivity for getting system info
 * Created by Joshua on 2020-2-22 16:47:44.
 */
public class SystemInfoActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_info);
        textView = findViewById(R.id.textView);
        StringBuilder sb = new StringBuilder();
        sb.append("android.os.Build:").append("\n")
                .append("Build.BOARD:").append(Build.BOARD).append("\n")
                .append("Build.BRAND:").append(Build.BRAND).append("\n");
        sb.append("java.lang.System:").append("\n")
                .append("System.getProperty(\"os.version\"):").append(System.getProperty("os.version")).append("\n")
                .append("System.getProperty(\"os.name\"):").append(System.getProperty("os.name")).append("\n");
        textView.setText(sb);
    }

}
