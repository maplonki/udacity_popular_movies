package maplonki.popularmovies.viper.data.api;

import java.util.List;

import maplonki.popularmovies.viper.BuildConfig;
import maplonki.popularmovies.viper.data.DataStore;
import maplonki.popularmovies.viper.data.DataStoreCallback;
import maplonki.popularmovies.viper.entity.MovieModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hugo on 8/10/2016.
 */
public class ApiDataStore implements DataStore {

    @Override
    public void execute(String filter, final DataStoreCallback dataStoreCallback) {
        MovieService service = MovieServiceManager.getInstance().getRetrofit().create(MovieService.class);
        // TODO: 8/11/2016 change for params
        Call<List<MovieModel>> call;
        switch (filter) {
            default:
            case "popular":
                call = service.popularMovies(BuildConfig.API_KEY);
                break;
            case "top":
                call = service.topRatedMovies(BuildConfig.API_KEY);
                break;
        }
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                dataStoreCallback.dataLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {

            }
        });
    }
}
