package com.lhf.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lhf.jetpack.compose.ComposeActivity
import com.lhf.jetpack.coroutine.CoroutineActivity
import com.lhf.jetpack.databinding.ActivityJetpackMainBinding
import com.lhf.jetpack.flow.FlowActivity
import com.lhf.jetpack.hilt.HiltActivity
import com.lhf.jetpack.room.RoomActivity

/**
 * Jetpack模块主界面
 *
 * @author Joshua
 * @date 2023/1/8 16:00
 */
class JetpackMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityJetpackMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetpackMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTestRoom.setOnClickListener {
            startActivity(Intent(this, RoomActivity::class.java))
        }
        binding.btnTestHilt.setOnClickListener {
            startActivity(Intent(this, HiltActivity::class.java))
        }
        binding.btnTestCoroutine.setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }
        binding.btnTestFlow.setOnClickListener {
            startActivity(Intent(this, FlowActivity::class.java))
        }
        binding.btnTestFlow.setOnClickListener {
            startActivity(Intent(this, ComposeActivity::class.java))
        }
    }
}