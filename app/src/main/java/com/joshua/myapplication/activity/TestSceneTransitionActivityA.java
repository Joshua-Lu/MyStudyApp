package com.joshua.myapplication.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity之间的过渡动画
 * <p>
 * Created by Joshua on 2020/9/26 22:18
 */
public class TestSceneTransitionActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scene_transition_a);
    }

    public void startActivityB(int type) {
        Intent intent = new Intent(TestSceneTransitionActivityA.this, TestSceneTransitionActivityB.class);
        intent.putExtra("type", type);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void explode(View view) {
        startActivityB(0);
    }

    public void slide(View view) {
        startActivityB(1);
    }

    public void fade(View view) {
        startActivityB(2);
    }

    public void share(View view) {
        Intent intent = new Intent(TestSceneTransitionActivityA.this, TestSceneTransitionActivityB.class);
        intent.putExtra("type", 3);
        // sharedElementName要与xml中定义的android:transitionName="share"一致
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                this, Pair.create(view, "share"))
                .toBundle());
    }
}