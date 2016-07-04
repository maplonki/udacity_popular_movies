package com.maplonki.popular_movies.fragments;

import android.app.Activity;
import android.app.Fragment;

import com.maplonki.popular_movies.interfaces.FragmentCallback;

/**
 * Created by hugo on 4/12/16.
 */
public class BaseFragment extends Fragment {

    protected FragmentCallback mFragmentCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mFragmentCallback = (FragmentCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement FragmentCallback");
        }
    }
}
