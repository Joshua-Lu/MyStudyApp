package com.joshua.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.joshua.myapplication.R

class TestIntentChooserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_intent_chooser)
    }

    fun startIntentChooser(view: View) {
        // 即使没有可选的应用，也会弹出选择器来提示
//        val chooserIntent = Intent.createChooser(Intent(Intent.ACTION_SEND), "自定义的应用选择器")
        // 有多个可选应用，就会弹出选择器，供用户选择，但不同于系统默认的选择器，这个没有让用户选择“始终使用”或“仅本次使用”某个应用来启动
        val chooserIntent = Intent.createChooser(Intent(Intent.ACTION_VIEW), "自定义的应用选择器")
        startActivity(chooserIntent)
    }
}