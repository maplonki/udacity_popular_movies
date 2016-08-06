package maplonki.popularmovies.mvvm.util;

import android.os.Bundle;

/**
 * Created by hugo on 4/12/16.
 */
public interface FragmentCallback {
    public void addFragment(int container, Class fragment, Bundle params);
}
