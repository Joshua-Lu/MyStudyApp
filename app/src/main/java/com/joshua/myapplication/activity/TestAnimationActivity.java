package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.joshua.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试视图动画animation
 * 注：视图动画并不会改变View的真实布局属性值（不同于属性动画animator）
 * Created by Joshua on 2020-1-8 22:58:49.
 */
public class TestAnimationActivity extends AppCompatActivity {

    private static final int DURATION = 2000;
    private AlphaAnimation alphaAnimation;
    private RotateAnimation rotateAnimation;
    private TranslateAnimation translateAnimation;
    private ScaleAnimation scaleAnimation;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        initAnim();
    }

    private void initAnim() {
        // 透明度动画
        alphaAnimation = new AlphaAnimation(1, 0.2f);
        alphaAnimation.setDuration(DURATION);

        // 旋转动画
//        // 旋转中心默认是View的左上角
//        rotateAnimation = new RotateAnimation(0, 360);
//        // 旋转坐标系原点默认是View的左上角
//        rotateAnimation = new RotateAnimation(0, 360,
//                DisplayUtil.getScreenWidth()/2, 0);
        // 可以指定旋转坐标系，ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT
        rotateAnimation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(DURATION);

        // 位移动画
        translateAnimation = new TranslateAnimation(0, 200,
                0, 500);
        translateAnimation.setDuration(DURATION);

        // 缩放动画
        scaleAnimation = new ScaleAnimation(0, 2, 0, 2);
        scaleAnimation.setDuration(DURATION);

        // 动画集合
        animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);
    }

    public void doAlphaAnim(View view) {
        // 视图动画完成后，默认都会恢复到原始状态，设置该值为true可保持最后的状态
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    public void doRotateAnim(View view) {
        view.startAnimation(rotateAnimation);
    }

    public void doTranslateAnim(View view) {
        view.startAnimation(translateAnimation);
    }

    public void doScaleAnim(View view) {
        view.startAnimation(scaleAnimation);
    }

    public void doAnimationSetAnim(View view) {
        view.startAnimation(animationSet);
    }
}
