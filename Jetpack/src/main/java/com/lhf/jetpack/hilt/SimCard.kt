package com.lhf.jetpack.hilt

import android.util.Log
import javax.inject.Inject

/**
 * 普通对象注入
 *
 * @author Joshua
 * @date 2023/1/8 14:24
 */
class SimCard @Inject constructor() {

    fun dialNumber() {
        Log.d(HiltActivity.TAG, "dialNumber: 拨打电话")
    }
}