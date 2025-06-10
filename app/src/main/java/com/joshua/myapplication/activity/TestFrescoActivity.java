package com.joshua.myapplication.activity;

import android.os.Bundle;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joshua.myapplication.R;
import com.joshua.myapplication.utils.Constants;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试Fresco使用
 *
 * @author Joshua
 * @date 2021/4/6 23:42
 */
public class TestFrescoActivity extends AppCompatActivity {


    SimpleDraweeView ivFresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fresco);

        ivFresco = findViewById(R.id.iv_fresco);
//        ivFresco.setImageURI(Constants.imageUrl);
        ivFresco.setImageURI(Constants.gifUrl);

        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER)
                .build();
        ivFresco.setHierarchy(hierarchy);
    }
}