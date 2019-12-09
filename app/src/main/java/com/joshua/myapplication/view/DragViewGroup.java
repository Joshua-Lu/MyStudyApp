package com.joshua.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

/**
 * 该ViewGroup通过ViewDragHelper类，实现内部子View跟随手指拖动效果
 * <p>
 * Created by Joshua on 2019/12/6.
 */
public class DragViewGroup extends LinearLayout {

    private static final String TAG = "DragViewGroup";
    private ViewDragHelper mViewDragHelper;
    private View child0;
    private View child1;
    private boolean canDrag = false;

    public DragViewGroup(@NonNull Context context) {
        this(context, null);
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }


    /**
     * 设置是否可拖动
     *
     * @param canDrag 拖动开关
     */
    public void setCanDrag(boolean canDrag) {
        this.canDrag = canDrag;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child0 = getChildAt(0);
        child1 = getChildAt(1);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            Log.d(TAG, "tryCaptureView() called with: child = [" + child + "], pointerId = [" + pointerId + "]");
            if (!canDrag) {
                Log.e(TAG, "onTouchEvent: canDrag = " + canDrag);
                return false;
            }
            return true;// 所有子View都可拖动
//            return child == child0;// 根据child判断是否可拖动
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            Log.d(TAG, "clampViewPositionHorizontal() called with: child = [" + child + "], left = [" + left + "], dx = [" + dx + "]");
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 将触摸事件传递给ViewDragHelper
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
