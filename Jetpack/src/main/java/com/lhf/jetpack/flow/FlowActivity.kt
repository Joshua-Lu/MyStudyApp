package com.lhf.jetpack.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lhf.jetpack.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        lifecycleScope.launch {
            // 调用collect后，才会订阅flow的数据
            loadData().collect {
                Log.d(TAG, "onCreate: loadData().collect:${Thread.currentThread().name} $it")
            }
        }
    }

    private fun loadData() = flow {
        Log.d(TAG, "loadData:${Thread.currentThread().name} start")
        for (i in 1..10) {
            delay(500)
            // 发送数据
            emit(i)
        }
    }.filter {
        // 使用filter可以对数据进行过滤
        it % 2 == 0
    }.map {
        // 使用map可以对数据进行转换
        "$it: String"
    }.flowOn(Dispatchers.IO)// 切换flow后面代码执行所在的线程

    companion object {
        private const val TAG = "FlowActivity"
    }
}