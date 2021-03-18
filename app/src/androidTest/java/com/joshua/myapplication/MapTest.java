package com.joshua.myapplication;

import android.util.SparseArray;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MapTest {

    public static final int TEST_COUNT = 25000;
    public long start;
    public long end;

    @Test
    public void testSparseArray() {
        SparseArray<String> sparseArray = new SparseArray<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            sparseArray.put(getKey(), "value" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("MapTest.testSparseArray:put cost time = [" + (end - start) + "]");

        start = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            sparseArray.get(getKey());
        }
        end = System.currentTimeMillis();
        System.out.println("MapTest.testSparseArray:get cost time = [" + (end - start) + "]");

//        start = System.currentTimeMillis();
//        for (int i = 0; i < TEST_COUNT; i++) {
//            sparseArray.delete(getKey());
//        }
//        end = System.currentTimeMillis();
//        System.out.println("MapTest.testSparseArray:delete cost time = [" + (end - start) + "]");
    }

    @Test
    public void testHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            hashMap.put(getKey(), "value" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("MapTest.testHashMap:put cost time = [" + (end - start) + "]");

        start = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            hashMap.get(getKey());
        }
        end = System.currentTimeMillis();
        System.out.println("MapTest.testHashMap:get cost time = [" + (end - start) + "]");

    }

    private int getKey() {
        return (int) (Math.random() * TEST_COUNT);
    }
}
