package maplonki.popularmovies.viper.view.fragment;

import android.app.ProgressDialog;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import maplonki.popularmovies.viper.R;
import maplonki.popularmovies.viper.entity.MovieModel;
import maplonki.popularmovies.viper.presenter.GridPresenter;
import maplonki.popularmovies.viper.presenter.GridPresenterInput;
import maplonki.popularmovies.viper.presenter.GridPresenterOutput;
import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;
import maplonki.popularmovies.viper.view.activity.MainActivity;
import maplonki.popularmovies.viper.view.adapter.MovieGridAdapter;


/**
 * Created by hugo on 4/11/16.
 */
public class MovieGridFragment extends BaseFragment implements GridPresenterOutput, View.OnClickListener {
    private static final int GRID_SPAN_COUNT = 3;

    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;

    // TODO: 8/10/2016 add dependency injection
    private GridPresenterInput mPresenter = new GridPresenter();
    private MovieGridAdapter movieGridAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setView(this);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.dialog_loading_movies));

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieGridAdapter = new MovieGridAdapter(getActivity(), this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_SPAN_COUNT));
        mRecyclerView.setAdapter(movieGridAdapter);

        ((MainActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.main_toolbar));
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onViewShow();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_popular:
                mPresenter.loadMovies("popular");
                break;
            case R.id.menu_top:
                mPresenter.loadMovies("top");
                break;
        }
        return true;
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    public void setMovieList(List<MovieViewModel> movieList) {
        movieGridAdapter.setObjectList(movieList);
    }

    @Override
    public void onClick(View view) {
        int itemPosition = mRecyclerView.getChildAdapterPosition(view);
        mPresenter.onMovieSelected(movieGridAdapter.getItem(itemPosition));
    }

   /* @Override
    public void onClick(View v) {
        int itemPosition = mRecyclerView.getChildAdapterPosition(v);
        MovieModel selectedMovie = mMovieList.get(itemPosition);
        if (selectedMovie != null) {
            Bundle params = new Bundle();
            params.putParcelable(Constants.EXTRAS_KEY_MOVIE, selectedMovie);
            openDetailFragment(params);
        }
    }

    private void openDetailFragment(Bundle params) {
        if (mFragmentCallback != null) {
            if (getResources().getBoolean(R.bool.isTablet)) {
                mFragmentCallback.addFragment(R.id.main_detail, MovieDetailFragment.class, params);
            } else {
                mFragmentCallback.addFragment(R.id.main_container, MovieDetailFragment.class, params);
            }
        }
    }*/
}
