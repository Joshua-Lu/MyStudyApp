package com.lhf.jetpack.room

import androidx.room.*

/**
 *
 * @author Joshua
 * @date 2022/9/1 8:03
 */
@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Delete
    fun delete(student: Student): Int

    @Query("DELETE FROM student WHERE name = :name")
    fun deleteByName(name: String): Int

    @Update
    fun update(student: Student): Int

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>
}