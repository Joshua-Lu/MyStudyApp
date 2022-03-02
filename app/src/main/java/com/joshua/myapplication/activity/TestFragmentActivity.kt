package com.joshua.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.joshua.myapplication.R
import com.joshua.myapplication.fragment.*

/**
 * 测试Fragment的使用，不同设备使用不同的布局
 *
 * @author Joshua
 * @date 2022/3/3 0:43
 */
class TestFragmentActivity : AppCompatActivity(), MyItemRecyclerViewAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)
        val frgList: ItemFragment = supportFragmentManager.findFragmentById(R.id.frg_list) as ItemFragment
        frgList.setListener(this)

    }

    override fun onItemClick(id: String, content: String) {
        Log.d(TAG, "onItemClick() called with: id = $id, content = $content")
        val flDetail = findViewById<FrameLayout>(R.id.fl_detail)
        if (flDetail != null) {
            val detailFragment = DetailFragment.newInstance(id, content)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_detail, detailFragment)
            // 添加fragment到回退栈中
            transaction.addToBackStack(null)
            // 设置fragment切换动画
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        } else {
            Intent(this, DetailFragmentActivity::class.java).apply {
                putExtra(ARG_PARAM1, id)
                putExtra(ARG_PARAM2, content)
                startActivity(this)
            }

        }
    }

    companion object {
        private const val TAG = "TestFragmentActivity"
    }
}