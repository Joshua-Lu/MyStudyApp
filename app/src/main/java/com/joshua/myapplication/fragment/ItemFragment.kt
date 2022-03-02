package com.joshua.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joshua.myapplication.R
import com.joshua.myapplication.fragment.dummy.DummyContent

/**
 * 列表页Fragment
 *
 * @author Joshua
 * @date 2022/3/3 0:46
 */
class ItemFragment() : Fragment(), MyItemRecyclerViewAdapter.OnItemClickListener {

    private var columnCount = 1

    private var listener: MyItemRecyclerViewAdapter.OnItemClickListener? = null

    fun setListener(listener: MyItemRecyclerViewAdapter.OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS, MyOnClickListener())
            }
        }
        return view
    }

    inner class MyOnClickListener : MyItemRecyclerViewAdapter.OnItemClickListener {
        override fun onItemClick(id: String, content: String) {
            listener?.onItemClick(id, content)
        }

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                ItemFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }

    override fun onItemClick(id: String, content: String) {
        TODO("Not yet implemented")
    }
}