package com.joshua.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_test_drag;
    Button btn_test_viewDragHelper;
    Button btn_test_draw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_test_drag = findViewById(R.id.btn_test_drag);
        btn_test_drag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(TestDragActivity.class);
            }
        });

        btn_test_viewDragHelper = findViewById(R.id.btn_test_viewDragHelper);
        btn_test_viewDragHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(TestViewDragHelperActivity.class);
            }
        });

        btn_test_draw = findViewById(R.id.btn_test_draw);
        btn_test_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestActivity(TestDrawActivity.class);
            }
        });
    }

    private void startTestActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
