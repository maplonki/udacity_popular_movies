package com.maplonki.popular_movies.fragments;

import android.app.ProgressDialog;
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

import com.maplonki.popular_movies.BuildConfig;
import com.maplonki.popular_movies.MConstants;
import com.maplonki.popular_movies.R;
import com.maplonki.popular_movies.activities.MainActivity;
import com.maplonki.popular_movies.adapters.MovieGridAdapter;
import com.maplonki.popular_movies.api.MovieService;
import com.maplonki.popular_movies.api.MovieServiceManager;
import com.maplonki.popular_movies.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hugo on 4/11/16.
 */
public class MovieGridFragment extends BaseFragment implements View.OnClickListener {
    private static final int GRID_SPAN_COUNT = 3;
    private List<MovieModel> mMovieList;

    private boolean mIsPopular;

    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;

    private Callback<List<MovieModel>> movieRequestListener = new Callback<List<MovieModel>>() {
        @Override
        public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
            mProgressDialog.dismiss();
            mMovieList = response.body();
            initRecyclerView();
        }

        @Override
        public void onFailure(Call<List<MovieModel>> call, Throwable t) {
            mProgressDialog.dismiss();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (savedInstanceState != null) {
            mMovieList = savedInstanceState.getParcelableArrayList(MConstants.EXTRAS_KEY_MOVIE_LIST);
            mIsPopular = savedInstanceState.getBoolean(MConstants.EXTRAS_KEY_IS_POPULAR);
        }
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
        ((MainActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.main_toolbar));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);

        if (mMovieList != null) {
            ((MainActivity) getActivity()).setActionBarTitle(mIsPopular);
            initRecyclerView();
        } else {
            requestMovies(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_popular:
                requestMovies(true);
                break;
            case R.id.menu_top:
                requestMovies(false);
                break;
        }
        return true;
    }

    private void initRecyclerView() {
        if (mRecyclerView != null && mMovieList != null) {
            Bundle params = new Bundle();
            params.putParcelable(MConstants.EXTRAS_KEY_MOVIE, mMovieList.get(0));
            if (getResources().getBoolean(R.bool.isTablet)) {
                openDetailFragment(params);
            }

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_SPAN_COUNT));
            mRecyclerView.setAdapter(new MovieGridAdapter(getActivity(), mMovieList, this));
        }
    }

    private void requestMovies(boolean isPopular) {
        mIsPopular = isPopular;

        //Each time we change, we set the title
        ((MainActivity) getActivity()).setActionBarTitle(isPopular);

        mProgressDialog = ProgressDialog.show(
                getActivity(),
                null,
                getString(R.string.dialog_loading_movies)
        );

        MovieService service = MovieServiceManager.getInstance().getRetrofit().create(MovieService.class);
        if (isPopular) {
            service.popularMovies(BuildConfig.API_KEY).enqueue(movieRequestListener);
        } else {
            service.topRatedMovies(BuildConfig.API_KEY).enqueue(movieRequestListener);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MConstants.EXTRAS_KEY_MOVIE_LIST, (ArrayList<MovieModel>) mMovieList);
        outState.putBoolean(MConstants.EXTRAS_KEY_IS_POPULAR, mIsPopular);
    }

    @Override
    public void onClick(View v) {
        int itemPosition = mRecyclerView.getChildAdapterPosition(v);
        MovieModel selectedMovie = mMovieList.get(itemPosition);
        if (selectedMovie != null) {
            Bundle params = new Bundle();
            params.putParcelable(MConstants.EXTRAS_KEY_MOVIE, selectedMovie);
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
    }
}
