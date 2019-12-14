package com.joshua.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 该View实现自身跟随手指拖动效果
 * <p>
 * Created by Joshua on 2019/12/3 .
 */
public class DragView extends View {

    private static final String TAG = "DragView";
    private int lastX;
    private int lastY;
    private int toX;
    private int toY;
    int x0;// DragView的初始屏幕x坐标
    int y0;// DragView的初始屏幕y坐标
    int[] location = new int[2];
    private boolean canDrag = true;

    public DragView(Context context) {
        this(context, null);
    }

    // 只在xml里使用View时，调用的时该两个参数的构造方法
    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);// 不设这个onTouchEvent无法正常回调
    }

    /**
     * 设置是否可拖动
     * @param canDrag 拖动开关
     */
    public void setCanDrag(boolean canDrag) {
        this.canDrag = canDrag;
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
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent() called with: event = [" + event + "]");
        if (!canDrag) {
            Log.e(TAG, "onTouchEvent: canDrag = " + canDrag);
            return super.onTouchEvent(event);
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d(TAG, "onTouchEvent: x = [" + x + "], y = [" + y + "]");
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        Log.d(TAG, "onTouchEvent: rawX = [" + rawX + "], rawY = [" + rawY + "]");
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                toX = -(rawX - x - x0);
                toY = -(rawY - y - y0);
                Log.d(TAG, "onTouchEvent ACTION_DOWN: toX = [" + toX + "], toY = [" + toY + "]");
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                Log.d(TAG, "onTouchEvent ACTION_MOVE: offsetX = [" + offsetX + "], offsetY = [" + offsetY + "]");

                // 方式一：使用layout
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

                // 方式二：使用offsetLeftAndRight和offsetTopAndBottom
                // 推荐使用该方式，简单易懂
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);

                // 方式三：使用scrollBy或scrollTo
                // 注意：scrollBy或scrollTo移动的时View的视图，相当于反方向移动里面的内容
                // 因此该方式移动的时父View里的所有内容
                // 不推荐该方式
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);

//                toX -= offsetX;
//                toY -= offsetY;
//                Log.d(TAG, "onTouchEvent ACTION_MOVE: toX = [" + toX + "], toY = [" + toY + "]");
//                ((View) getParent()).scrollTo(toX, toY);
                break;
        }
        return super.onTouchEvent(event);
    }

}
