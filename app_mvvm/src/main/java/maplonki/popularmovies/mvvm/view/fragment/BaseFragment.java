package maplonki.popularmovies.mvvm.view.fragment;

import android.app.Activity;
import android.app.Fragment;

import maplonki.popularmovies.mvvm.util.FragmentCallback;

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
