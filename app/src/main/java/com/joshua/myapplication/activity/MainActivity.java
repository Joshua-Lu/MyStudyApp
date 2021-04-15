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
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
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
        clsList.add(SystemInfoActivity.class);
        clsList.add(TestAlarmManagerActivity.class);
        clsList.add(TestCustomViewActivity.class);
        clsList.add(AidlClientActivity.class);
        clsList.add(TestClipChildrenActivity.class);
        clsList.add(TestSceneTransitionActivityA.class);
        clsList.add(TestMaterialDesignActivity.class);
        clsList.add(TestNotificationActivity.class);
        clsList.add(TestContentObserverActivity.class);
        clsList.add(TestViewUtilsActivity.class);
        clsList.add(TestHandlerInThreadActivity.class);
        clsList.add(TestViewPagerActivity.class);
        clsList.add(TestGlideActivity.class);
        clsList.add(TestFrescoActivity.class);
        clsList.add(TestMapActivity.class);
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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
