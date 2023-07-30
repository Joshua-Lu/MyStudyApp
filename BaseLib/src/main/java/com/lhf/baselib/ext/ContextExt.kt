package com.lhf.baselib.ext

import android.content.Context
import android.content.Intent

/**
 * Context扩展
 *
 * @author Joshua
 * @date 2023/7/30 23:10
 */

/**
 * 启动Activity
 * @receiver Context
 * @param block intent中传参
 */
inline fun <reified T> Context.startActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}
