package com.joshua.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import com.joshua.myapplication.database.Person;

/**
 * Created by Joshua on 2020/11/6 23:51
 */
public class DatabaseUtil {
    public static ContentValues getContentValues(Person person) {
        ContentValues values = new ContentValues();
        values.put(Constants.FIELD_ID, person.getId());
        values.put(Constants.FIELD_NAME, person.getName());
        values.put(Constants.FIELD_AGE, person.getAge());
        return values;
    }

    @SuppressLint("Range")
    public static Person getPerson(Cursor cursor) {
        Person person;
        person = new Person();
        person.setId(cursor.getInt(cursor.getColumnIndex(Constants.FIELD_ID)));
        person.setName(cursor.getString(cursor.getColumnIndex(Constants.FIELD_NAME)));
        person.setAge(cursor.getInt(cursor.getColumnIndex(Constants.FIELD_AGE)));
        return person;
    }
}
