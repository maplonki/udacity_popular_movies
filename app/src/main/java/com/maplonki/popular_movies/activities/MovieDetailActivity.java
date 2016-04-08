package com.maplonki.popular_movies.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.maplonki.popular_movies.MConstants;
import com.maplonki.popular_movies.R;
import com.maplonki.popular_movies.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by hugo on 4/6/16.
 * <p/>
 * This is the detail class, it shows a cover image at the top
 * and the details of the movie.
 */
public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.detail_collapsing_toolbar);
        MovieModel mMovieModel = getIntent().getParcelableExtra(MConstants.EXTRAS_KEY_MOVIE);

        if (mMovieModel != null) {
            if (collapsingToolbarLayout != null) {
                collapsingToolbarLayout.setTitle(mMovieModel.getTitle());
            }

            ImageView backdropImage = (ImageView) findViewById(R.id.detail_iv_backdrop);
            Picasso.with(this).load(MConstants.BASE_IMAGE_URL + mMovieModel.getBackdropPath())
                    .into(backdropImage);


            ImageView posterImage = (ImageView) findViewById(R.id.detail_iv_poster);
            Picasso.with(this).load(MConstants.BASE_IMAGE_URL + mMovieModel.getPosterPath())
                    .into(posterImage);


            TextView overview = (TextView) findViewById(R.id.detail_tv_overview);
            if (overview != null) {
                overview.setText(mMovieModel.getOverview());
            }

            TextView releaseDate = (TextView) findViewById(R.id.detail_tv_release_date);
            String formattedDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(mMovieModel.getReleaseDate());
            if (releaseDate != null) {
                releaseDate.setText(String.format(getString(R.string.release_date_format), formattedDate));
            }

            TextView rating = (TextView) findViewById(R.id.detail_tv_rating);
            int ratingInt = (int) mMovieModel.getVoteAverage();
            if (rating != null) {
                rating.setText(String.format(getString(R.string.rating_format), ratingInt));
            }
        }


    }
}
