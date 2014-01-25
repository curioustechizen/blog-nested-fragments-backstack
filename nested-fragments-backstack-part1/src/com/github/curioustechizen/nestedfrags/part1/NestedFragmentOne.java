package com.github.curioustechizen.nestedfrags.part1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NestedFragmentOne extends BaseNestedFragment{

    public static NestedFragmentOne newInstance() {
        return new NestedFragmentOne();
    }

    public NestedFragmentOne(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLabelNested.setText("Name: ");
    }
}
