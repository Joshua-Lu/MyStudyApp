package com.joshua.myapplication.utils;

import android.content.Context;
import android.util.Log;

import com.lhf.javacommonlib.common.Book;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import androidx.test.platform.app.InstrumentationRegistry;

/**
 * Created by Joshua on 2021/1/6 0:30
 */
public class XmlPullParseUtilTest {

    private static final String TAG = "XmlPullParseUtilTest";

    @Test
    public void testPullParse() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileInputStream file = null;
        try {
            file = appContext.openFileInput("bookShelf.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Book> books = XmlPullParseUtil.pullParse(file);
        Log.d(TAG, "testPullParse: books = [" + books + "]");
        Assert.assertNotNull(books);
    }
}