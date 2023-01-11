package com.lhf.jetpack.room

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lhf.jetpack.databinding.ActivityRoomBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val studentDao = MyDataBase.getInstance(this).studentDao
        val zoro = Student()
        zoro.name = "zoro"
        zoro.age = 18
        binding.btnInsert.setOnClickListener {
            GlobalScope.launch {
                Log.d(TAG, "onCreate: insert $zoro")
                studentDao.insert(zoro)
            }
        }
        binding.btnDelete.setOnClickListener {
            GlobalScope.launch {
                zoro.id = 3
                Log.d(TAG, "onCreate: delete $zoro")
//                val delete = studentDao.delete(zoro)
                val delete = studentDao.deleteByName(zoro.name)
                Log.d(TAG, "onCreate: delete $delete")
            }
        }
        binding.btnUpdate.setOnClickListener {
            GlobalScope.launch {
                zoro.id = 4
                zoro.age = 11
                Log.d(TAG, "onCreate: update $zoro")
                val update = studentDao.update(zoro)
                Log.d(TAG, "onCreate: update $update")
            }
        }
        binding.btnQuery.setOnClickListener {
            GlobalScope.launch {
                val students = studentDao.getAll()
                Log.d(TAG, "onCreate: getAll size: ${students.size}")
                for (student in students) {
                    Log.d(TAG, "onCreate: getAll: $student")
                }
            }
        }
    }

    companion object {
        private const val TAG = "RoomActivity"
    }
}