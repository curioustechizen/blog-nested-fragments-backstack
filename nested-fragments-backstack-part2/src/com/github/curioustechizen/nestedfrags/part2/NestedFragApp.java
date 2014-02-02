package com.github.curioustechizen.nestedfrags.part2;

import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class NestedFragApp extends Application {

    Map<String, Fragment.SavedState> savedStateMap;

    @Override
    public void onCreate() {
        savedStateMap = new HashMap<String, Fragment.SavedState>();
        super.onCreate();
    }

    public void setFragmentSavedState(String key, Fragment.SavedState state){
        savedStateMap.put(key, state);
    }

    public Fragment.SavedState getFragmentSavedState(String key){
        return savedStateMap.get(key);
    }

}
