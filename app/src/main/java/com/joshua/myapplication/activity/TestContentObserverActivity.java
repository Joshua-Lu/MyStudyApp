package com.joshua.myapplication.activity;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.joshua.myapplication.R;
import com.joshua.myapplication.database.Person;
import com.joshua.myapplication.utils.Constants;
import com.joshua.myapplication.utils.DatabaseUtil;

import androidx.appcompat.app.AppCompatActivity;

public class TestContentObserverActivity extends AppCompatActivity {

    private static final String TAG = "TestContentObserverActi";
    public ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_observer);
        contentResolver = getContentResolver();
        contentResolver.registerContentObserver(Constants.personUri, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d(TAG, "onChange() called with: selfChange = [" + selfChange + "], uri = [" + uri + "]");
            }
        });
    }

    public void insert(View view) {
        Uri insertResult = contentResolver.insert(Constants.personUri, DatabaseUtil.getContentValues(new Person(1, "索隆", 20)));
//        contentResolver.notifyChange(Constants.personUri, null);
        Log.d(TAG, "insert: insertResult = [" + insertResult + "]");
    }
}