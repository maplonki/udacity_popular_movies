package maplonki.popularmovies.viper.interactor;

import java.util.ArrayList;
import java.util.List;

import maplonki.popularmovies.viper.BuildConfig;
import maplonki.popularmovies.viper.data.DataStore;
import maplonki.popularmovies.viper.data.DataStoreCallback;
import maplonki.popularmovies.viper.data.api.ApiDataStore;
import maplonki.popularmovies.viper.entity.MovieModel;
import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;

/**
 * Created by hugo on 8/10/2016.
 */
public class GridInteractor implements GridInteractorInput, DataStoreCallback {

    private GridInteractorOutput mPresenter;

    private DataStore mDataStore = new ApiDataStore();

    @Override
    public void setInteractorOutput(GridInteractorOutput presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void loadMovies(String filter) {
        mDataStore.execute(filter,this);
    }

    private List<MovieViewModel> setupViewmodel(List<MovieModel> body) {
        List<MovieViewModel> viewModelList = new ArrayList<>();
        for (MovieModel model : body) {
            viewModelList.add(new MovieViewModel(model));
        }
        return viewModelList;
    }

    @Override
    public void dataLoaded(List<MovieModel> modelList) {
        mPresenter.onMoviesLoaded(setupViewmodel(modelList));
    }
}
