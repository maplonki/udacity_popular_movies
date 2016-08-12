package maplonki.popularmovies.viper.interactor;

import java.util.List;

import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;

/**
 * Created by hugo on 8/10/2016.
 */
public interface GridInteractorOutput {
    void onMoviesLoaded(List<MovieViewModel> viewModelList);
}
