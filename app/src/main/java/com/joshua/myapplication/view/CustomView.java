package com.joshua.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import com.joshua.myapplication.R;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by Joshua on 2020/4/18 11:37.
 */
public class CustomView extends AppCompatTextView {

    private static final String TAG = "CustomView";

    /**
     * 在Java代码中直接new一个CustomView实例的时候，会调用该构造函数
     *
     * @param context
     */
    public CustomView(Context context) {
        this(context, null);
    }

    /**
     * 在xml中引用CustomView标签的时候，会调用2参数的构造函数。
     * 这种方式通常是我们需要自定义View的属性的时候，使用2参数的构造函数。
     *
     * @param context
     * @param attrs
     */
    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 3个参数的构造函数一般是由我们主动调用的，如：上面2个参数的构造函数调用。
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView, R.attr.customDefStyle, R.style.CustomDefStyleRes);
        // defStyleAttr为0时，defStyleRes才生效
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, R.style.CustomDefStyleRes);
        if (ta != null) {
            String attr1 = ta.getString(R.styleable.CustomView_attr1);
            String attr2 = ta.getString(R.styleable.CustomView_attr2);
            String attr3 = ta.getString(R.styleable.CustomView_attr3);
            String attr4 = ta.getString(R.styleable.CustomView_attr4);
            Log.d("lhf-" + TAG, "init: attr1 = [" + attr1 + "]");
            Log.d("lhf-" + TAG, "init: attr2 = [" + attr2 + "]");
            Log.d("lhf-" + TAG, "init: attr3 = [" + attr3 + "]");
            Log.d("lhf-" + TAG, "init: attr4 = [" + attr4 + "]");
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("lhf-" + TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("lhf-" + TAG, "onLayout() called with: changed = [" + changed + "], l = [" + l + "], t = [" + t + "], r = [" + r + "], b = [" + b + "]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("lhf-" + TAG, "onDraw() called with: canvas = [" + canvas + "]");
    }
}
