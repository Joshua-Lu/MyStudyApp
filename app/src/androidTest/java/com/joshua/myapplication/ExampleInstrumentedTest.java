package com.joshua.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.util.LruCache;

import com.joshua.myapplication.utils.Constants;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Map;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String TAG = "ExampleInstrumentedTest";
    private static Context appContext;
    private Uri uri = Uri.parse("content://" + Constants.AUTHORITIES_PERSON + "/person");

    @BeforeClass
    public static void beforeClass() throws Exception {
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.joshua.myapplication", appContext.getPackageName());
    }

    @Test
    public void testContentObserver() {
        ContentResolver resolver = appContext.getContentResolver();
        resolver.registerContentObserver(uri, false, new ContentObserver(new Handler(appContext.getMainLooper())) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d(TAG, "onChange() called with: selfChange = [" + selfChange + "], uri = [" + uri + "]");
            }
        });

    }


    @Test
    public void testFormatFileSize() {
        File filesDir = appContext.getFilesDir();
        long totalSpace = filesDir.getTotalSpace();
        Log.d(TAG, "testFormatFileSize: totalSpace = [" + totalSpace + "]");
        String fileSize = Formatter.formatFileSize(appContext, totalSpace);
        Log.d(TAG, "testFormatFileSize: fileSize = [" + fileSize + "]");
    }

    @Test
    public void testLruCache() {
        LruCache<Integer, String> lruCache = new LruCache<>(5);
        lruCache.put(1, "1");
        lruCache.put(2, "2");
        lruCache.put(3, "3");
        lruCache.put(4, "4");
        lruCache.put(5, "5");
        lruCache.put(6, "6");
        lruCache.get(2);
        lruCache.get(1);
        System.out.println("ExampleInstrumentedTest.testLruCache: lruCache = [" + lruCache + "]");
        Map<Integer, String> snapshot = lruCache.snapshot();
        System.out.println("ExampleInstrumentedTest.testLruCache: lruCache.map = [" + snapshot + "]");
    }
}
