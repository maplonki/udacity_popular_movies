package maplonki.popularmovies.viper.presenter;

import maplonki.popularmovies.viper.entity.MovieModel;
import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;

/**
 * Created by hugo on 8/8/2016.
 */
public interface GridPresenterInput {
    void setView(GridPresenterOutput view);
    void onViewShow();
    void loadMovies(String filter);
    void onMovieSelected(MovieViewModel movieModel);
}
