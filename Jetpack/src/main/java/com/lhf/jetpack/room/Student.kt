package com.lhf.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author Joshua
 * @date 2022/9/1 7:54
 */
@Entity(tableName = "student")
class Student() {

    // 主键
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var name = ""

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age = 0


    override fun toString(): String {
        return "Student(id=$id, name='$name', age=$age)"
    }


}