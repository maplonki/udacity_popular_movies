package maplonki.popularmovies.viper.data;

import java.util.List;

import maplonki.popularmovies.viper.entity.MovieModel;

/**
 * Created by hugo on 8/10/2016.
 */
public interface DataStoreCallback {
    void dataLoaded(List<MovieModel> modelList);
}
