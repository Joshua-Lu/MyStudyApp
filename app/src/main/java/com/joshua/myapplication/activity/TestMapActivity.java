package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

import com.joshua.myapplication.R;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试 HashMap 与 SparseArray 的性能
 * <p>
 * 时间性能：数据少时，SparseArray 快，数据多时 HashMap 快
 * 经简单测试数据在2万5左右，时间差不多，之后 SparseArray 性能下降明显
 * <p>
 * 空间性能：HashMap 占用空间是 SparseArray 的两三倍
 *
 * @author Joshua
 * @date 2021/4/15 23:55
 */
public class TestMapActivity extends AppCompatActivity {

    public static final int TEST_COUNT = 25000;
    public long start;
    public long end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_map);
    }

    public void testHashMap(View view) {
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

    public void testSparseArray(View view) {
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
    }

    private int getKey() {
        return (int) (Math.random() * TEST_COUNT);
    }
}