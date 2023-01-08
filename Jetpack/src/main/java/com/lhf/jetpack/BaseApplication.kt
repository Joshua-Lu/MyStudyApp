package com.lhf.jetpack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 测试依赖注入，添加注解 @HiltAndroidApp
 *
 * @author Joshua
 * @date 2023/1/8 13:45
 */
@HiltAndroidApp
class BaseApplication : Application() {
}