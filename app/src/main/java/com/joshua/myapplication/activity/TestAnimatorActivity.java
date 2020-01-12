package com.joshua.myapplication.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.IntProperty;
import android.view.View;

import com.joshua.myapplication.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试属性动画animator
 * 属性动画会真实的改变View的属性
 * Created by Joshua on 2020-1-12 17:42:14.
 */
public class TestAnimatorActivity extends AppCompatActivity {

    private static final int DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animator);
    }

    public void doAlphaAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,
                View.ALPHA,
                0.2f);
        animator.setDuration(DURATION);
        animator.start();
    }

    public void doRotateAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,
                View.ROTATION_X,
                200);
        animator.setDuration(DURATION).start();
    }

    public void doTranslateAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,
                "translationY",
                1200);
        animator.setDuration(DURATION).start();
    }

    public void doScaleAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,
                View.SCALE_X,
                0.5f);
        animator.setDuration(DURATION).start();
    }

    public void doAnimationSetAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,
                View.SCALE_X,
                0.5f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(
                view,
                View.SCALE_Y,
                0.5f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(
                view,
                "translationY",
                1000);
        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(animator, animator1);// 动画同时播放
//        animatorSet.playSequentially(animator,animator1);// 动画依次播放
        animatorSet.play(animator).before(animator1).with(animator2).after(3000);// 自定义动画顺序
        animatorSet.setDuration(DURATION).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void doPropertyAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofInt(
                view,
                new IntProperty<View>("with") {
                    @Override
                    public void setValue(View object, int value) {
                        object.getLayoutParams().width = value;
                        object.requestLayout();
                    }

                    @Override
                    public Integer get(View object) {
                        return object.getLayoutParams().width;
                    }
                }, 500);
        animator.setDuration(DURATION).start();
    }

    class WrapperView {

        private View mTarget;
        private int bgColor;

        public WrapperView(View mTarget) {
            this.mTarget = mTarget;
        }

        public int getBgColor() {
            return bgColor;
        }

        /**
         * set方法是关键，必须要有
         * 构造ObjectAnimator对象的propertyName与该方法对应
         */
        public void setBgColor(int bgColor) {
            this.bgColor = bgColor;
            mTarget.setBackgroundColor(bgColor);
        }

    }

    public void doWrapperViewAnim(View view) {
        ObjectAnimator animator = ObjectAnimator.ofInt(
                new WrapperView(view),
                "bgColor"
                , Color.RED);
        animator.setDuration(DURATION).start();
    }

    public void doValueAnimatorAnim(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);
//        ValueAnimator valueAnimator = new ValueAnimator();
//        valueAnimator.setFloatValues(0, 360);
        valueAnimator.setTarget(view);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();
                view.setRotation(animatedValue);
            }
        });
        valueAnimator.setDuration(DURATION).start();
    }

    public void doPropertyValuesHolderAnim(View view) {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 200);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, 0, 1);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, 0, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2, pvh3);
        objectAnimator.setDuration(DURATION).start();
    }
}
