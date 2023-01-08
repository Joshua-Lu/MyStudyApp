package com.lhf.jetpack.hilt

import android.util.Log

/**
 * 模拟第三方库 OkHttpClient
 *
 * @author Joshua
 * @date 2023/1/8 16:20
 */
class OkHttpClient {

    fun newCall() {
        Log.d(HiltActivity.TAG, "newCall() called")
    }
}