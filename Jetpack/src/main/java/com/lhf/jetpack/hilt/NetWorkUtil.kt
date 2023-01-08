package com.lhf.jetpack.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * 第三方库依赖注入示例
 *
 * @author Joshua
 * @date 2023/1/8 16:16
 */
@Module
@InstallIn(ApplicationComponent::class)
class NetWorkUtil {

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}