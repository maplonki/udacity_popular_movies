package com.maplonki.popular_movies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maplonki.popular_movies.MConstants;
import com.maplonki.popular_movies.R;
import com.maplonki.popular_movies.activities.MainActivity;
import com.maplonki.popular_movies.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by hugo on 4/11/16.
 */
public class MovieDetailFragment extends BaseFragment {

    private MovieModel mMovieModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieModel = getArguments().getParcelable(MConstants.EXTRAS_KEY_MOVIE);

        if (!getResources().getBoolean(R.bool.isTablet)) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.detail_toolbar));

        if (mMovieModel != null) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.detail_collapsing_toolbar);

            if (collapsingToolbarLayout != null) {
                collapsingToolbarLayout.setTitle(mMovieModel.getTitle());
            }

            ImageView backdropImage = (ImageView) view.findViewById(R.id.detail_iv_backdrop);
            Picasso.with(getActivity()).load(MConstants.BASE_IMAGE_URL + mMovieModel.getBackdropPath())
                    .into(backdropImage);


            ImageView posterImage = (ImageView) view.findViewById(R.id.detail_iv_poster);
            Picasso.with(getActivity()).load(MConstants.BASE_IMAGE_URL + mMovieModel.getPosterPath())
                    .into(posterImage);


            TextView overview = (TextView) view.findViewById(R.id.detail_tv_overview);
            if (overview != null) {
                overview.setText(mMovieModel.getOverview());
            }

            TextView releaseDate = (TextView) view.findViewById(R.id.detail_tv_release_date);
            String formattedDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(mMovieModel.getReleaseDate());
            if (releaseDate != null) {
                releaseDate.setText(String.format(getString(R.string.release_date_format), formattedDate));
            }

            TextView rating = (TextView) view.findViewById(R.id.detail_tv_rating);
            int ratingInt = (int) mMovieModel.getVoteAverage();
            if (rating != null) {
                rating.setText(String.format(getString(R.string.rating_format), ratingInt));
            }
        }
    }
}
