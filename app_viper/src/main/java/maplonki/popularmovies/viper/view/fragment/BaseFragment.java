package maplonki.popularmovies.viper.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import maplonki.popularmovies.viper.R;
import maplonki.popularmovies.viper.common.FragmentCallback;


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

    public void setActionBarTitle(String title) {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
