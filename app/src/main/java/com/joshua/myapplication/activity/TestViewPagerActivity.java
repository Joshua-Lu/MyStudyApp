package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.util.Log;

import com.joshua.myapplication.R;
import com.joshua.myapplication.view.viewpager.MyFragmentPagerAdapter;
import com.joshua.myapplication.view.viewpager.MyPagerAdapter;
import com.joshua.myapplication.view.viewpager.PagerBaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Joshua
 * @date 2021/4/3 16:16
 */
public class TestViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "TestViewPagerActivity";
    public ViewPager viewPager;
    private List<String> list = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_pager);

        viewPager = findViewById(R.id.viewPager);

        initData();

        setListener();

        setAdapter();

    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected() called with: position = [" + position + "]");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged() called with: state = [" + state + "]");

            }
        });
        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
                Log.d(TAG, "onAdapterChanged() called with: viewPager = [" + viewPager + "], oldAdapter = [" + oldAdapter + "], newAdapter = [" + newAdapter + "]");
            }
        });
    }

    private void setAdapter() {
//        setSimpleAdapter();
        setFragmentPagerAdapter();
    }

    private void setFragmentPagerAdapter() {
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentPagerAdapter.setData(fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
    }

    private void setSimpleAdapter() {
        PagerAdapter pagerAdapter = new MyPagerAdapter(list);
        viewPager.setAdapter(pagerAdapter);
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            list.add("page " + i);
            fragmentList.add(PagerBaseFragment.newInstance("fragment page " + i));
        }
    }
}