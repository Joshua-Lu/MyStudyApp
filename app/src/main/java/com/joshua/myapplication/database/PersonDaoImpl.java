package com.joshua.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joshua.myapplication.utils.Constants;
import com.joshua.myapplication.utils.DatabaseUtil;

import java.util.ArrayList;

/**
 * Created by Joshua on 2020/11/6 1:00
 */
public class PersonDaoImpl implements IPersonDao {

    private final DatabaseHelper databaseHelper;

    public PersonDaoImpl(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public long add(Person person) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = DatabaseUtil.getContentValues(person);
        long rowID = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return rowID;
    }

    @Override
    public int deleteById(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int rowCounts = db.delete(Constants.TABLE_NAME, Constants.FIELD_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rowCounts;
    }

    @Override
    public int update(Person person) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = DatabaseUtil.getContentValues(person);
        int rowCounts = db.update(Constants.TABLE_NAME, values, Constants.FIELD_ID + "=?", new String[]{String.valueOf(person.getId())});
        db.close();
        return rowCounts;
    }

    @Override
    public Person queryByID(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, null, Constants.FIELD_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Person person = null;
        while (cursor.moveToNext()) {
            person = DatabaseUtil.getPerson(cursor);
        }
        cursor.close();
        db.close();
        return person;
    }

    @Override
    public ArrayList<Person> listAll() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Person> people = new ArrayList<>();
        while (cursor.moveToNext()) {
            people.add(DatabaseUtil.getPerson(cursor));
        }
        db.close();
        return people;
    }
}
