package com.joshua.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.joshua.myapplication.utils.Constants;

import androidx.annotation.Nullable;

/**
 * Created by Joshua on 2020/11/6 0:01
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called with: db = [" + db + "]");
        String createSql = "create table if not exists " + Constants.TABLE_NAME + "(" +
                Constants.FIELD_ID + " integer primary key , " +
                Constants.FIELD_NAME + " varchar , " +
                Constants.FIELD_AGE + " integer)";
        db.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
