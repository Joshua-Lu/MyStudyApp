package com.joshua.myapplication.activity;

import android.os.Bundle;

import com.joshua.myapplication.R;
import com.joshua.myapplication.view.PointAnimView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Joshua
 * @date 2022/12/18 15:36
 */
public class TestPointAnimViewActivity extends AppCompatActivity {

    public PointAnimView pointAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_point_anim_view);

        pointAnimView = findViewById(R.id.pointAnimView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        pointAnimView.post(() -> pointAnimView.startMyAnimation());
    }

    @Override
    protected void onPause() {
        super.onPause();
        pointAnimView.stopMyAnimation();
    }

}