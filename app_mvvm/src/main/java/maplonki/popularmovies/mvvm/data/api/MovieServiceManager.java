package maplonki.popularmovies.mvvm.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import maplonki.popularmovies.mvvm.model.MovieModel;
import maplonki.popularmovies.mvvm.util.Constants;
import maplonki.popularmovies.mvvm.util.json.MovieDeserializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hugo on 4/7/16.
 * <p/>
 * This is a helper instances which wraps the initialization for the retrofit
 * library
 */
public class MovieServiceManager {
    private static MovieServiceManager ourInstance = new MovieServiceManager();

    public static MovieServiceManager getInstance() {
        return ourInstance;
    }

    private Retrofit retrofit;

    private MovieServiceManager() {
        Gson myGson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<MovieModel>>() {
                }.getType(), new MovieDeserializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(myGson))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
