package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joshua.myapplication.R;
import com.joshua.myapplication.viewutils.FindViewById;
import com.joshua.myapplication.viewutils.SetOnClickListener;
import com.joshua.myapplication.viewutils.ViewUtils;

import androidx.appcompat.app.AppCompatActivity;

public class TestViewUtilsActivity extends AppCompatActivity {

    private static final String TAG = "TestViewUtilsActivity";

    @FindViewById(R.id.textView)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_utils);
        // 这行必须写在所有使用View的地方前面
        ViewUtils.init(this);

        textView.setText("不用自己写findViewById，可以直接使用了！");
        Log.d(TAG, "onCreate: textView = [" + textView.getText() + "]");
    }

    @SetOnClickListener(R.id.button)
    private void onClick(View view) {
        Toast.makeText(this, "不用自己写SetOnClickListener，可以实现点击事件了！", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onClick: view = [" + view + "]");
    }
}