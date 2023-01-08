package com.lhf.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lhf.jetpack.room.RoomActivity
import kotlinx.android.synthetic.main.activity_jetpack_main.*

class JetpackMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack_main)

        btnTestRoom.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
    }
}