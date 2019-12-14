package com.joshua.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * 该View通过Scroller实现View里面内容随手指拖动
 * <p>
 * Created by Joshua on 2019/12/7.
 */
public class ScrollerView extends ConstraintLayout {

    private static final String TAG = "ScrollerView";
    Scroller mScroller;
    private int lastX;
    private int lastY;
    private int startX;
    private int startY;
    int x0;// DragView的初始屏幕x坐标
    int y0;// DragView的初始屏幕y坐标
    int[] location = new int[2];
    boolean canScroll = true;

    public ScrollerView(Context context) {
        this(context, null);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        mScroller = new Scroller(context);
    }


    /**
     * 设置是否可拖动
     * @param canScroll 滚动开关
     */
    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getLocationOnScreen(location);
        x0 = location[0];
        y0 = location[1];
        Log.d(TAG, "onLayout: x0 = [" + x0 + "], y0 = [" + y0 + "]");
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent() called with: event = [" + event + "]");
        if (!canScroll) {
            Log.e(TAG, "onTouchEvent: canScroll = " + canScroll);
            return super.onTouchEvent(event);
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                Log.d(TAG, "onTouchEvent ACTION_MOVE: startX = [" + startX + "], startY = [" + startY + "]");
                Log.d(TAG, "onTouchEvent ACTION_MOVE: offsetX = [" + offsetX + "], offsetY = [" + offsetY + "]");
                mScroller.startScroll(startX, startY, -offsetX, -offsetY);
                startX -= offsetX;
                startY -= offsetY;
                lastX = x;
                lastY = y;
                // 通过重绘来实现不断调用computeScroll
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
