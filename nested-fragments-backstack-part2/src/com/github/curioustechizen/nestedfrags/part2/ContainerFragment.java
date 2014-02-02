package com.github.curioustechizen.nestedfrags.part2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A fragment that in turn contains other fragments
 */
public class ContainerFragment extends Fragment implements View.OnClickListener {

    private Button mBtn;

    /*
     * Flag to keep track of what fragment is currently being shown,
     * For example purposes only. Do not use such an approach in production code.
     */
    private int mCurrentlyShowingFragment;
    private static final String SAVED_STATE_KEY = ContainerFragment.class.getSimpleName();

    public static ContainerFragment newInstance() {
        return new ContainerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_container, container, false);
        mBtn = (Button) fragmentView.findViewById(R.id.btn_nested_frag);
        mBtn.setOnClickListener(this);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SavedState fragmentSavedState = ((NestedFragApp)getActivity().getApplication()).getFragmentSavedState(SAVED_STATE_KEY);
        if(fragmentSavedState == null){
            if (savedInstanceState == null) {
                getChildFragmentManager().beginTransaction().replace(R.id.nested_fragment_container, NestedFragmentOne.newInstance()).commit();
                mCurrentlyShowingFragment = 0;
            } else {
                mCurrentlyShowingFragment = savedInstanceState.getInt("currently_showing_fragment");
            }
        }
        adjustButtonText();
    }

    private void adjustButtonText() {
        if(mCurrentlyShowingFragment == 0){
            mBtn.setText("Next");
        } else {
            mBtn.setText("Previous");
        }
    }

    @Override
    public void onClick(View v) {
        if (mCurrentlyShowingFragment == 0) {
            showNextScreen();
        } else {
            showPreviousScreen();
        }
    }

    private void showNextScreen() {
        mCurrentlyShowingFragment = 1;
        getChildFragmentManager().beginTransaction().replace(R.id.nested_fragment_container, NestedFragmentTwo.newInstance()).addToBackStack(null).commit();
        adjustButtonText();
    }

    private void showPreviousScreen() {
        mCurrentlyShowingFragment = 0;
        getChildFragmentManager().popBackStackImmediate();
        adjustButtonText();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currently_showing_fragment", mCurrentlyShowingFragment);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((NestedFragApp)getActivity().getApplication()).setFragmentSavedState(SAVED_STATE_KEY, getFragmentManager().saveFragmentInstanceState(this));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((NestedFragApp)getActivity().getApplication()).setFragmentSavedState(SAVED_STATE_KEY, null);
    }
}
