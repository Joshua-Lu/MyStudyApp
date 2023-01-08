package com.lhf.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lhf.jetpack.room.RoomActivity
import kotlinx.android.synthetic.main.activity_jetpack_main.*

/**
 * Jetpack模块主界面
 *
 * @author Joshua
 * @date 2023/1/8 16:00
 */
class JetpackMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_main)

        btnTestRoom.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}