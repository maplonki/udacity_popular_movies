package com.maplonki.popular_movies.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.maplonki.popular_movies.BuildConfig;
import com.maplonki.popular_movies.MConstants;
import com.maplonki.popular_movies.R;
import com.maplonki.popular_movies.adapters.MovieGridAdapter;
import com.maplonki.popular_movies.api.MovieService;
import com.maplonki.popular_movies.api.MovieServiceManager;
import com.maplonki.popular_movies.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            mMovieList = savedInstanceState.getParcelableArrayList(MConstants.EXTRAS_KEY_MOVIE_LIST);
            mIsPopular = savedInstanceState.getBoolean(MConstants.EXTRAS_KEY_IS_POPULAR);
        }

        if (mMovieList != null) {
            setActionBarTitle(mIsPopular);
            initRecyclerView();
        } else {
            requestMovies(true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        if (mRecyclerView != null && mMovieList != null) {
            mRecyclerView.setAdapter(new MovieGridAdapter(MainActivity.this, mMovieList, this));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, GRID_SPAN_COUNT));
        }
    }

    private void requestMovies(boolean isPopular) {
        mIsPopular = isPopular;

        //Each time we change, we set the title
        setActionBarTitle(isPopular);

        mProgressDialog = ProgressDialog.show(
                MainActivity.this,
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

    private void setActionBarTitle(boolean isPopular) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(isPopular ?
                    getString(R.string.title_popular_movies) :
                    getString(R.string.title_top_movies));
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
            Intent detailIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
            detailIntent.putExtra(MConstants.EXTRAS_KEY_MOVIE, selectedMovie);
            startActivity(detailIntent);
        }
    }
}
