package com.joshua.myapplication.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.joshua.myapplication.R

/**
 * 自定义字体TextView
 * 1.在main目录下新建assets目录
 * 2.在assets目录下新建fonts目录
 * 3.将字体文件放入fonts目录下
 * 4.在代码中设置字体样式
 * 5.在xml中使用
 *
 * @author Joshua
 * @date 2023/5/7 16:50
 */
class CustomFontTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    /**
     * 初始化
     */
    private fun init(context: Context) {
        //设置自定义字体样式
        typeface = FontCustom.setFont(context)
        // 设置字体大小
        textSize = 20f
        // 设置自定义背景
        background = context.resources.getDrawable(R.drawable.shape_round_corner_bg)
    }

    class FontCustom {
        companion object {
            /**
             * 设置字体
             */
            fun setFont(context: Context): Typeface? {
                // 设置字体样式
                return Typeface.createFromAsset(context.assets, "fonts/ysbth.TTF")
            }
        }

    }


}