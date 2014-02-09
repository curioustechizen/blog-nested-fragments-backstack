package com.github.curioustechizen.nestedfrags.part3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.curioustechizen.nestedfrags.part3.R;

public abstract class BaseNestedFragment extends Fragment {

    protected TextView mLabelNested;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_content, container, false);
        mLabelNested = (TextView) fragmentView.findViewById(R.id.nested_label);
        return fragmentView;
    }

}
