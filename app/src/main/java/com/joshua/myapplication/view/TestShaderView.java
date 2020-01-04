package com.joshua.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * for testing Shader of Paint
 * <p>
 * Created by Joshua on 2020/1/4 22:07.
 */
public class TestShaderView extends View {

    public TestShaderView(Context context) {
        this(context, null);
    }

    public TestShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestShaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

}
