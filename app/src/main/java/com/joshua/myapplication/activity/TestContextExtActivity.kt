package com.joshua.myapplication.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.joshua.myapplication.R
import com.lhf.baselib.ext.startActivity

class TestContextExtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_context_ext)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            startActivity<TestContextExtActivity> {
                putExtra("test", "simple start activity by ContextExt")
            }
        }

        val extra = intent.getStringExtra("test")
        extra?.let {
            findViewById<TextView>(R.id.tv_msg).text = it
        }
    }


}