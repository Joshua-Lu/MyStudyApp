package com.joshua.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView


/**
 * test ExoPlayer
 * 从 ExoPlayer 2.18 版本开始，Google 正式将 ExoPlayer 移植到 androidx.media3 包下，作为 Jetpack 组件的一部分。
 *
 * Created by Joshua on 2025-6-6
 */
class TestExoPlayerActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null
    private var playerView: PlayerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        ActivityTestExoPlayerBinding.inflate(layoutInflater)
        setContentView(com.joshua.myapplication.R.layout.activity_test_exo_player)

        // 获取 PlayerView 实例
        playerView = findViewById(com.joshua.myapplication.R.id.playerView)

        // 创建 ExoPlayer 实例
        player = ExoPlayer.Builder(this).build()

        // 将播放器绑定到 PlayerView
        playerView?.player = player

        // 创建 MediaItem（可以是本地或网络资源）
        val mediaItem: MediaItem = MediaItem.fromUri("https://googledownloads.cn/cn-devsite/sTIBDcyCmCg.mp4")

        // 设置媒体项到播放器
        player?.setMediaItem(mediaItem)

        // 准备播放器
        player?.prepare()

        // 开始播放
        player?.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 释放播放器资源
        player?.release()
        player = null
    }
}