package com.joshua.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.joshua.myapplication.R
import com.joshua.myapplication.fragment.ARG_PARAM1
import com.joshua.myapplication.fragment.ARG_PARAM2
import com.joshua.myapplication.fragment.DetailFragment

/**
 * DetailFragment对应的Activity
 *
 * @author Joshua
 * @date 2022/3/3 0:44
 */
class DetailFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fragment)
        val frgDetail: DetailFragment = supportFragmentManager.findFragmentById(R.id.frg_detail) as DetailFragment
        val param1 = intent.getStringExtra(ARG_PARAM1)
        val param2 = intent.getStringExtra(ARG_PARAM2)
        frgDetail.setParams(param1, param2)
    }
}