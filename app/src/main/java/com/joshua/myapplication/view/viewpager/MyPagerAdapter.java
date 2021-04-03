package com.joshua.myapplication.view.viewpager;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author Joshua
 * @date 2021/4/3 14:41
 */
public class MyPagerAdapter extends PagerAdapter {

    private static final String TAG = "MyPagerAdapter";

    List<String> list = new ArrayList<>();

    public MyPagerAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount() called list.size() = " + list.size());
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.d(TAG, "isViewFromObject() called with: view = [" + view + "], object = [" + object + "]");
        return view == object;
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        Log.d(TAG, "startUpdate() called with: container = [" + container + "]");
        super.startUpdate(container);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem() called with: container = [" + container + "], position = [" + position + "]");
        TextView view = new TextView(container.getContext());
        view.setText(getItemData(position));
        container.addView(view);
        return view;
    }

    private String getItemData(int position) {
        return list.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem() called with: container = [" + container + "], position = [" + position + "], object = [" + object + "]");
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "setPrimaryItem() called with: container = [" + container + "], position = [" + position + "], object = [" + object + "]");
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        Log.d(TAG, "finishUpdate() called with: container = [" + container + "]");
        super.finishUpdate(container);
    }
}
