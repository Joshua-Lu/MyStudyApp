package com.lhf.jetpack.coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * 测试viewModelScope使用
 *
 * @author Joshua
 * @date 2023/1/9 23:17
 */
class MyViewModel : ViewModel() {
    fun testViewModelScope() {
        viewModelScope.launch {
            Log.d(
                CoroutineActivity.TAG,
                "ViewModel testViewModelScope: viewModelScope.launch : " + Thread.currentThread().name
            )
        }
    }
}