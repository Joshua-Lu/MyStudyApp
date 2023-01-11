package com.lhf.jetpack.coroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lhf.jetpack.R
import kotlinx.coroutines.*

/**
 * 测试协程
 *
 * @author Joshua
 * @date 2023/1/8 22:18
 */
class CoroutineActivity : AppCompatActivity() {

    val myViewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        Log.d(TAG, "onCreate: GlobalScope：开始")
        GlobalScope.launch {
            Log.d(TAG, "onCreate: GlobalScope: " + Thread.currentThread().name)
            delay(2000)
            Log.d(TAG, "onCreate: GlobalScope: 结束")
        }

        Log.d(TAG, "onCreate: runBlocking：开始")
        runBlocking {
            // 还是在主线程里执行
            Log.d(TAG, "onCreate: runBlocking: " + Thread.currentThread().name)
            delay(1000)
            Log.d(TAG, "onCreate: runBlocking: 结束")
        }

        Log.d(TAG, "onCreate: CoroutineScope：开始")
        val job = Job()
        CoroutineScope(job).launch {
            Log.d(TAG, "onCreate: CoroutineScope: " + Thread.currentThread().name)
            delay(1000)
            Log.d(TAG, "onCreate: CoroutineScope: 结束")
        }

        Log.d(TAG, "onCreate: CoroutineScope async：开始")
        CoroutineScope(job).launch {
            val res = async {
                Log.d(TAG, "onCreate: CoroutineScope async: " + Thread.currentThread().name)
                delay(1000)
                "async 结束"
            }
            Log.d(TAG, "onCreate: async 结果：" + res.await())
        }

        Log.d(TAG, "onCreate: CoroutineScope withContext：开始")
        CoroutineScope(job).launch(Dispatchers.Main) {
            val res = withContext(Dispatchers.IO) {
                Log.d(TAG, "onCreate: CoroutineScope withContext: " + Thread.currentThread().name)
                delay(1000)
                "withContext 结束"
            }
            Log.d(TAG, "onCreate: async 结果：$res")
        }

        lifecycleScope.launch {
            Log.d(
                TAG, "onCreate: lifecycleScope.launch : " + Thread.currentThread().name + ", lifecycle" +
                        ".currentState: " + lifecycle.currentState
            )
        }

        lifecycleScope.launchWhenCreated {
            Log.d(
                TAG, "onCreate: lifecycleScope.launchWhenCreated : " + Thread.currentThread().name + ", lifecycle" +
                        ".currentState: " + lifecycle.currentState
            )
            lifecycle.currentState
        }

        lifecycleScope.launchWhenResumed {
            Log.d(
                TAG, "onCreate: lifecycleScope.launchWhenResumed : " + Thread.currentThread().name + ", lifecycle" +
                        ".currentState: " + lifecycle.currentState
            )
        }

        lifecycleScope.launchWhenStarted {
            Log.d(
                TAG, "onCreate: lifecycleScope.launchWhenStarted : " + Thread.currentThread().name + ", lifecycle" +
                        ".currentState: " + lifecycle.currentState
            )
        }

        myViewModel.testViewModelScope()

        Log.d(TAG, "onCreate: 主线程最后一行")
    }

    companion object {
        const val TAG = "CoroutineActivity"
    }
}