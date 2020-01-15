package com.joshua.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.joshua.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private List<Map<String, String>> data;
    private SimpleAdapter adapter;
    private List<Class<?>> clsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initClsList();
        initData();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new SimpleAdapter(MainActivity.this, data,
                android.R.layout.simple_list_item_1, new String[]{"activity"}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: position = [" + position + "]");
                startTestActivity(position);
            }
        });
    }

    private void initClsList() {
        clsList = new ArrayList<>();
        clsList.add(TestDragActivity.class);
        clsList.add(TestViewDragHelperActivity.class);
        clsList.add(TestDrawActivity.class);
        clsList.add(TestXmlDrawActivity.class);
        clsList.add(TestImagePsActivity.class);
        clsList.add(TestPorterDuffModeActivity.class);
        clsList.add(TestShaderActivity.class);
        clsList.add(TestSurfaceViewActivity.class);
        clsList.add(TestAnimationActivity.class);
        clsList.add(TestAnimatorActivity.class);
        clsList.add(TestFloatViewActivity.class);
    }

    public void initData() {
        data = new ArrayList<>();
        for (Class<?> activity : clsList) {
            Map<String, String> map = new HashMap<>();
            map.put("activity", "start " + activity.getSimpleName());
            data.add(map);
        }
    }

    private void startTestActivity(int position) {
        if (position < 0 || position >= clsList.size()) {
            Log.e(TAG, "startTestActivity: positon is invalid！");
            return;
        }
        startTestActivity(clsList.get(position));
    }

    private void startTestActivity(Class<?> cls) {
        Log.d(TAG, "startTestActivity: cls.getName() = [" + cls.getName() + "]");
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

}
