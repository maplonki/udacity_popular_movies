package maplonki.popularmovies.viper.application;

import android.app.Application;

import org.stringtemplate.v4.compiler.STParser;

/**
 * Created by hugo on 8/11/2016.
 */
public class MoviesApplication extends Application {

    private static MoviesApplication instance;

    public static MoviesApplication getInstance() {
        if (instance == null) {
            instance = new MoviesApplication();
        }
        return instance;
    }
}
