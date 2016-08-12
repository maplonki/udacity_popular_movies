package maplonki.popularmovies.viper.common;

import android.os.Bundle;

/**
 * Created by hugo on 4/12/16.
 */
public interface FragmentCallback {
    void addFragment(int container, Class fragment, Bundle params);
}
