package com.joshua.myapplication.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.joshua.myapplication.database.Person;
import com.joshua.myapplication.utils.Constants;
import com.joshua.myapplication.utils.DatabaseUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import androidx.test.platform.app.InstrumentationRegistry;

/**
 * Created by Joshua on 2020/11/6 23:36
 */
public class PersonProviderTest {

    private static final String TAG = "PersonProviderTest";
    private ContentResolver contentResolver;
    private Uri uri;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        contentResolver = appContext.getContentResolver();
        uri = Uri.parse("content://" + Constants.AUTHORITIES_PERSON + "/person");
    }

    @After
    public void tearDown() throws Exception {
        Log.d(TAG, "tearDown: ===================================================");
    }

    @Test
    public void query() {
        Cursor cursor = contentResolver.query(uri, null, "id=? and age=?", new String[]{"1", "20"}, null);
        if (cursor == null) {
            Log.e(TAG, "query: cursor == null");
            return;
        }
        while (cursor.moveToNext()) {
            Person person = DatabaseUtil.getPerson(cursor);
            Log.d(TAG, "query: person = [" + person + "]");
        }
        cursor.close();
    }

    @Test
    public void listAll() {
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null) {
            Log.e(TAG, "query: cursor == null");
            return;
        }
        while (cursor.moveToNext()) {
            Person person = DatabaseUtil.getPerson(cursor);
            Log.d(TAG, "query: person = [" + person + "]");
        }
        cursor.close();
    }

    @Test
    public void getType() {
        String type = contentResolver.getType(uri);
        Log.d(TAG, "getType: type = [" + type + "]");
    }

    @Test
    public void insert() {
        Uri insertResult = contentResolver.insert(uri, DatabaseUtil.getContentValues(new Person(1, "索隆", 20)));
        Log.d(TAG, "insert: insertResult = [" + insertResult + "]");
    }

    @Test
    public void delete() {
        int deleteResult = contentResolver.delete(uri, "id=?", new String[]{"3"});
        Log.d(TAG, "delete: deleteResult = [" + deleteResult + "]");
    }

    @Test
    public void update() {
        int updateResult = contentResolver.update(uri,
                DatabaseUtil.getContentValues(new Person(3, "罗宾", 18)),
                "id=?",
                new String[]{"3"});
        Log.d(TAG, "update: updateResult = [" + updateResult + "]");
    }
}