package com.maplonki.popular_movies.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.maplonki.popular_movies.R;
import com.maplonki.popular_movies.fragments.MovieGridFragment;
import com.maplonki.popular_movies.interfaces.FragmentCallback;

public class MainActivity extends AppCompatActivity implements FragmentCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(R.id.main_container, MovieGridFragment.class, new Bundle());

    }

    public void setActionBarTitle(boolean isPopular) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(isPopular ?
                    getString(R.string.title_popular_movies) :
                    getString(R.string.title_top_movies));
        }
    }

    @Override
    public void addFragment(int container, Class fragment, Bundle params) {
        getFragmentManager()
                .beginTransaction()
                .add(container, Fragment.instantiate(MainActivity.this, fragment.getName(), params))
                .addToBackStack(null)
                .commit();
    }
}
