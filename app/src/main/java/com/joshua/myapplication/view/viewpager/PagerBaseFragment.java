package com.joshua.myapplication.view.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joshua.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagerBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagerBaseFragment extends Fragment {

    private static final String TAG = "PagerBaseFragment";
    private static final String ARG_NAME = "name";

    private String name;

    public PagerBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment FirstFragment.
     */
    public static PagerBaseFragment newInstance(String name) {
        PagerBaseFragment fragment = new PagerBaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
        Log.d(TAG, "onCreate: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        TextView tvName = rootView.findViewById(R.id.tv_name);
        tvName.setText(name);
        Log.d(TAG, "onCreateView: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: name = [" + name + "], isVisibleToUser = [" + isVisibleToUser + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: name = [" + name + "], getLifecycle().getCurrentState() = [" + getLifecycle().getCurrentState() + "]");
    }
}