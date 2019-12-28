package com.joshua.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startTestActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    public void btnTestDragClick(View view) {
        startTestActivity(TestDragActivity.class);
    }

    public void btnTestViewDragHelperClick(View view) {
        startTestActivity(TestViewDragHelperActivity.class);
    }

    public void btnTestDrawClick(View view) {
        startTestActivity(TestDrawActivity.class);
    }

    public void btnTestXmldrawClick(View view) {
        startTestActivity(TestXmlDrawActivity.class);
    }

    public void btnTestImagePsClick(View view) {
        startTestActivity(TestImagePsActivity.class);
    }
}
