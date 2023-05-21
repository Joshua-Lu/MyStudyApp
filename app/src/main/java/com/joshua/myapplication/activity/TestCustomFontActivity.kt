package com.joshua.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joshua.myapplication.R
import com.joshua.myapplication.view.CustomFontTextView

/**
 * 测试自定义字体
 *
 * @author Joshua
 * @date 2023/5/21 19:28
 */
class TestCustomFontActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_custom_font)
        // 获取自定义字体TextView
        val customFontTextView = findViewById<CustomFontTextView>(R.id.customFontTextView)
        // 设置字体大小
        customFontTextView.textSize = 20f
        // 设置点击事件
        customFontTextView.setOnClickListener {
            // 设置放大两倍同时旋转一圈的动画
            customFontTextView.animate().rotation(360f).duration = 2000
            customFontTextView.animate().scaleX(2f).scaleY(2f).duration = 2000
            // 设置透明度到0.1,耗时3秒的动画
            customFontTextView.animate().alpha(0.1f).duration = 3000
        }
        // 打印屏幕分辨率和像素密度
        val displayMetrics = resources.displayMetrics
        val density = displayMetrics.density
        val densityDpi = displayMetrics.densityDpi
        val widthPixels = displayMetrics.widthPixels
        val heightPixels = displayMetrics.heightPixels
        val scaledDensity = displayMetrics.scaledDensity
        val xdpi = displayMetrics.xdpi
        val ydpi = displayMetrics.ydpi
        //  使用android的Log打印日志
        android.util.Log.d("TestCustomFontActivity", "density: $density")
        android.util.Log.d("TestCustomFontActivity", "densityDpi: $densityDpi")
        android.util.Log.d("TestCustomFontActivity", "widthPixels: $widthPixels")
        android.util.Log.d("TestCustomFontActivity", "heightPixels: $heightPixels")
        android.util.Log.d("TestCustomFontActivity", "scaledDensity: $scaledDensity")
        android.util.Log.d("TestCustomFontActivity", "xdpi: $xdpi")
        android.util.Log.d("TestCustomFontActivity", "ydpi: $ydpi")


    }
}