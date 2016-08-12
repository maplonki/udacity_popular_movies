package maplonki.popularmovies.viper.presenter;

import java.util.List;

import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;

/**
 * Created by hugo on 8/8/2016.
 */
public interface GridPresenterOutput {
    void showProgress();
    void hideProgress();
    void setMovieList(List<MovieViewModel> movieList);
}
