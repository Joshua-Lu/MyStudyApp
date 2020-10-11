package com.joshua.myapplication.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class TestMaterialDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_material_design);

        View btn8 = findViewById(R.id.button8);
        btn8.setOnClickListener(v -> {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    v, v.getWidth() / 2, v.getHeight() / 2, 0, v.getWidth());
            animator.setDuration(2000).start();
        });

        View btn9 = findViewById(R.id.button9);
        btn9.setOnClickListener(v -> {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    v, 0, 0, 0, (float) Math.hypot(v.getWidth(), v.getHeight()));
            animator.setDuration(2000).start();
        });

        View btn10 = findViewById(R.id.button10);
        StateListAnimator animator = AnimatorInflater.loadStateListAnimator(this, R.drawable.anim_change);
        btn10.setStateListAnimator(animator);
    }
}