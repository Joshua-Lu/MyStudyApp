package com.joshua.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 该View实现自身跟随手指拖动效果
 *
 * Created by Joshua on 2019/12/3 .
 */
public class DragView extends View {

    private static final String TAG = "DragView";
    private int lastX;
    private int lastY;

    public DragView(Context context) {
        this(context, null);
    }

    // 只在xml里使用View时，调用的时该两个参数的构造方法
    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.d(TAG, "layout() called with: l = [" + l + "], t = [" + t + "], r = [" + r + "], b = [" + b + "]");
        super.layout(l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent() called with: event = [" + event + "]");
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d(TAG, "onTouchEvent: x = [" + x + "], y = [" + y + "]");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);
                break;
        }
        return super.onTouchEvent(event);
    }

}
