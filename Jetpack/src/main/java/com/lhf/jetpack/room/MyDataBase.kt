package com.lhf.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *
 * @author Joshua
 * @date 2022/9/1 8:11
 */
@Database(entities = [Student::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract val studentDao: StudentDao

    companion object {
        private const val DB_NAME = "test_room.db"

        private var INSTANCE: MyDataBase? = null

        fun getInstance(context: Context): MyDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, MyDataBase::class.java, DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}