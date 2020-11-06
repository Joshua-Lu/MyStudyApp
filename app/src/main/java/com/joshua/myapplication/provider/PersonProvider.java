package com.joshua.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.joshua.myapplication.database.DatabaseHelper;
import com.joshua.myapplication.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PersonProvider extends ContentProvider {

    private static final String TAG = "PersonProvider";
    private DatabaseHelper databaseHelper;
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int MATCHCODE_PERSON = 1;

    static {
        uriMatcher.addURI(Constants.AUTHORITIES_PERSON, "person", MATCHCODE_PERSON);
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int matchCode = uriMatcher.match(uri);
        Log.d("lhf-" + TAG, "query: matchCode = [" + matchCode + "]");
        if (matchCode == MATCHCODE_PERSON) {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.query(Constants.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int matchCode = uriMatcher.match(uri);
        return String.valueOf(matchCode);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long insert = db.insert(Constants.TABLE_NAME, null, values);
        Uri uriResult = Uri.withAppendedPath(uri, String.valueOf(insert));
        return uriResult;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int deleteResult = db.delete(Constants.TABLE_NAME, selection, selectionArgs);
        return deleteResult;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int updateResult = db.update(Constants.TABLE_NAME, values, selection, selectionArgs);
        return updateResult;
    }

}
