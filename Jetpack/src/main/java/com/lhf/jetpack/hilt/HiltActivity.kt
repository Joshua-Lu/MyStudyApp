package com.lhf.jetpack.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 测试依赖注入
 *
 * @author Joshua
 * @date 2023/1/8 16:03
 */
@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    // 该对象就不用手动创建对象，会自动注入
    // 普通对象注入
    @Inject
    lateinit var mobilePhone: MobilePhone

    // 第三方库对象注入
    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mobilePhone.dialNumber()

        okHttpClient.newCall()
    }

    companion object {
        const val TAG = "HiltActivity"
    }
}