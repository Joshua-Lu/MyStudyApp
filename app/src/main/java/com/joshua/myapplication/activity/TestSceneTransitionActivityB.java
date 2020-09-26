package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Window;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class TestSceneTransitionActivityB extends AppCompatActivity {
    private static final String TAG = "TransitionB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestFeature必须放在setContentView之前
        //否则报异常：AndroidRuntimeException: requestFeature() must be called before adding content
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_test_scene_transition_b);

        int type = getIntent().getIntExtra("type", 0);
        Log.d("lhf-" + TAG, "onCreate: type = [" + type + "]");
        switch (type) {
            case 0:
                // 分解
                getWindow().setEnterTransition(new Explode());
                getWindow().setExitTransition(new Explode());
                break;
            case 1:
                // 滑动
                getWindow().setEnterTransition(new Slide());
                getWindow().setExitTransition(new Slide());
                break;
            case 2:
                // 淡出
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                break;
            case 3:
                Transition transition = new ChangeBounds().setDuration(3000);
//                Transition transition = new ChangeClipBounds().setDuration(3000);
//                Transition transition = new ChangeTransform().setDuration(3000);
//                Transition transition = new ChangeImageTransform().setDuration(3000);
                getWindow().setSharedElementEnterTransition(transition);
                getWindow().setSharedElementExitTransition(transition);
                break;
        }


    }
}