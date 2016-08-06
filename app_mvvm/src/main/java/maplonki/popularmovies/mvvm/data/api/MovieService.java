package maplonki.popularmovies.mvvm.data.api;

import java.util.List;

import maplonki.popularmovies.mvvm.model.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hugo on 4/5/16.
 * <p/>
 * This class defines the different resources
 * available from the Movies API
 */
public interface MovieService {
    @GET("movie/popular")
    Call<List<MovieModel>> popularMovies(@Query("api_key") String appId);

    @GET("movie/top_rated")
    Call<List<MovieModel>> topRatedMovies(@Query("api_key") String appId);

}
