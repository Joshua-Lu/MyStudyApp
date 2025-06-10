object Versions {
    const val compile_sdk_version = 33
    const val min_sdk_version = 21
    const val build_tools_version = "30.0.3"
    const val version_code = 1
    const val version_name = "1.0"

    const val arouter_api_version = "1.5.0"
    const val arouter_compiler_version = "1.2.2"

    const val kotlin_version = "1.7.10"
    const val room_version = "2.4.3"
    const val coroutines_version = "1.3.9"
    const val hilt_version = "2.28-alpha"
    const val android_hilt_version = "1.0.0-alpha01"
    const val lifecycle_version = "2.5.1"
    const val okhttp_version = "4.9.0"
    const val retrofit_version = "2.9.0"
    const val paging_version = "3.1.1"
    const val compose_ui_version = "1.1.1"
    const val butterknife_version = "10.2.3"
    const val glide_version = "4.12.0"
}

object Libs {
    // ARouter
    const val arouter_api = "com.alibaba:arouter-api:${Versions.arouter_api_version}"
    const val arouter_compiler = "com.alibaba:arouter-compiler:${Versions.arouter_compiler_version}"

    // androidx 常用库
    const val appcompat = "androidx.appcompat:appcompat:1.3.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val legacy_support_v4 = "androidx.legacy:legacy-support-v4:1.0.0"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.2.1"
    const val material = "com.google.android.material:material:1.5.0"
    const val junit = "junit:junit:4.13.2"
    const val test_junit = "androidx.test.ext:junit:1.1.3"
    const val test_espresso_core = "androidx.test.espresso:espresso-core:3.4.0"

    // glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"

    // fresco
    const val fresco = "com.facebook.fresco:fresco:2.3.0"

    // butter knife
    const val butterknife = "com.jakewharton:butterknife:${Versions.butterknife_version}"
    const val butterknife_compiler = "com.jakewharton:butterknife-compiler:${Versions.butterknife_version}"

    // RxJava
    const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.21"

    // RxJava Android扩展库
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.1"

    // okhttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"

    // retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"

    // retrofit 支持使用RxJava2CallAdapterFactory，将retrofit Call包装成RxJava的Observable
    const val retrofit_rxjava_adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0"

    // retrofit 支持使用GsonConverterFactory，返回Json对象
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    // EventBus
    const val eventbus = "org.greenrobot:eventbus:3.2.0"

    // gson
    const val gson = "com.google.code.gson:gson:2.8.6"

    // kotlin-stdlib
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$${Versions.kotlin_version}"

    // core-ktx
    const val core_ktx = "androidx.core:core-ktx:1.7.0"

    // Room
    const val room_runtime = "androidx.room:room-runtime:$${Versions.room_version}"
    const val room_compiler = "androidx.room:room-compiler:$${Versions.room_version}"

    // 协程
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$${Versions.coroutines_version}"
    const val kotlinx_coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$${Versions.coroutines_version}"

    // 包含协程lifecycleScope
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:$${Versions.lifecycle_version}"

    // 包含协程viewModelScope
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$${Versions.lifecycle_version}"

    //Dagger - Hilt
    const val hilt_android = "com.google.dagger:hilt-android:$${Versions.hilt_version}"
    const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:$${Versions.hilt_version}"

    // Android - Hilt
    const val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$${Versions.android_hilt_version}"
    const val hilt_compiler = "androidx.hilt:hilt-compiler:$${Versions.android_hilt_version}"

    // 可以使用 by viewModels 创建ViewModel对象
    const val activity_ktx = "androidx.activity:activity-ktx:1.6.1"

    // paging3
    const val paging_runtime = "androidx.paging:paging-runtime:$${Versions.paging_version}"
    const val paging_common = "androidx.paging:paging-common:$${Versions.paging_version}"

    // Compose
    const val activity_compose = "androidx.activity:activity-compose:1.3.1"
    const val compose_ui = "androidx.compose.ui:ui:$${Versions.compose_ui_version}"
    const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:$${Versions.compose_ui_version}"
    const val compose_material = "androidx.compose.material:material:1.1.1"
    const val compose_ui_test_junit = "androidx.compose.ui:ui-test-junit4:$${Versions.compose_ui_version}"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:$${Versions.compose_ui_version}"
    const val compose_ui_test_manifest = "androidx.compose.ui:ui-test-manifest:$${Versions.compose_ui_version}"

    // ExoPlayer
    const val exoplayer = "com.google.android.exoplayer:exoplayer:2.16.1"
}