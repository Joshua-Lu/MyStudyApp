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
    private View mMenuView;
    private View mMainView;
    private boolean canDrag = true;
    private int mWith;

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
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWith = mMenuView.getMeasuredWidth();
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            Log.d(TAG, "tryCaptureView() called with: child = [" + child + "], pointerId = [" + pointerId + "]");
            if (!canDrag) {
                Log.e(TAG, "onTouchEvent: canDrag = " + canDrag);
                return false;
            }
//            return true;// 所有子View都可拖动
            return child == mMainView;// 根据child判断是否可拖动
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            Log.d(TAG, "clampViewPositionHorizontal() called with: child = [" + child + "], left = [" + left + "], dx = [" + dx + "]");
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            Log.d(TAG, "clampViewPositionVertical() called with: child = [" + child + "], top = [" + top + "], dy = [" + dy + "]");
            return 0;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            Log.d(TAG, "onViewReleased() called with: releasedChild = [" + releasedChild + "], xvel = [" + xvel + "], yvel = [" + yvel + "]");
            // 手指抬起后移动到指定位置
            if (mMainView.getLeft() < mWith) {
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
            } else {
                mViewDragHelper.smoothSlideViewTo(mMainView, mWith, 0);
            }
            ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
        }

        @Override
        public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
            Log.d(TAG, "onViewCaptured() called with: capturedChild = [" + capturedChild + "], activePointerId = [" + activePointerId + "]");
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
            Log.d(TAG, "onViewDragStateChanged() called with: state = [" + state + "]");
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            Log.d(TAG, "onViewPositionChanged() called with: changedView = [" + changedView + "], left = [" + left + "], top = [" + top + "], dx = [" + dx + "], dy = [" + dy + "]");
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //固定写法
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_CANCEL
                || action == MotionEvent.ACTION_UP) {
            mViewDragHelper.cancel();
            return false;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 固定写法
        // 将触摸事件传递给ViewDragHelper，此操作必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        // 固定写法
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
