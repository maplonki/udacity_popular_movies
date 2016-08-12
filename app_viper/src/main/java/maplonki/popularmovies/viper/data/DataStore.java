package maplonki.popularmovies.viper.data;


import retrofit2.Callback;

/**
 * Created by hugo on 8/10/2016.
 */
public interface DataStore {
    void execute(String filter, DataStoreCallback dataStoreCallback);
}
