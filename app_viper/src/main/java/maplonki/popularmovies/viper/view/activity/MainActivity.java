package maplonki.popularmovies.viper.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


import maplonki.popularmovies.viper.R;
import maplonki.popularmovies.viper.common.FragmentCallback;
import maplonki.popularmovies.viper.view.fragment.MovieGridFragment;

/**
 * Created by hugo on 8/8/2016.
 */
public class MainActivity extends AppCompatActivity implements FragmentCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(R.id.main_container, MovieGridFragment.class, new Bundle());
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
