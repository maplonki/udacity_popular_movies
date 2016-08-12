package maplonki.popularmovies.viper.presenter;

import android.graphics.Movie;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import maplonki.popularmovies.viper.R;
import maplonki.popularmovies.viper.common.Constants;
import maplonki.popularmovies.viper.common.FragmentCallback;
import maplonki.popularmovies.viper.entity.MovieModel;
import maplonki.popularmovies.viper.interactor.GridInteractor;
import maplonki.popularmovies.viper.interactor.GridInteractorInput;
import maplonki.popularmovies.viper.interactor.GridInteractorOutput;
import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;
import maplonki.popularmovies.viper.view.fragment.BaseFragment;
import maplonki.popularmovies.viper.view.fragment.MovieDetailFragment;
import maplonki.popularmovies.viper.view.fragment.MovieGridFragment;

/**
 * Created by hugo on 8/8/2016.
 */
public class GridPresenter implements GridPresenterInput, GridInteractorOutput {

    private GridPresenterOutput mView;

    // TODO: 8/10/2016 add dependency injection
    private GridInteractorInput mInteractor = new GridInteractor();

    private List<MovieViewModel> viewModelList = new ArrayList<>();

    @Override
    public void setView(GridPresenterOutput view) {
        this.mView = view;
    }

    @Override
    public void onViewShow() {
        mInteractor.setInteractorOutput(this);
        loadMovies("popular");
    }

    @Override
    public void loadMovies(String filter) {
        mView.showProgress();
        mInteractor.loadMovies(filter);
    }

    @Override
    public void onMovieSelected(MovieViewModel movieModel) {
        // TODO: 8/11/2016 change for injected activity reference
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRAS_KEY_MOVIE, movieModel);
        ((FragmentCallback) ((BaseFragment) mView).getActivity()).addFragment(R.id.main_container, MovieDetailFragment.class, args);
    }

    @Override
    public void onMoviesLoaded(List<MovieViewModel> viewModelList) {
        mView.hideProgress();
        mView.setMovieList(viewModelList);
    }
}
