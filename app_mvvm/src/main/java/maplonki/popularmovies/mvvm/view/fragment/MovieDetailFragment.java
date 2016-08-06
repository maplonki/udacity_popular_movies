package maplonki.popularmovies.mvvm.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import maplonki.popularmovies.mvvm.R;
import maplonki.popularmovies.mvvm.databinding.FragmentMovieDetailBinding;
import maplonki.popularmovies.mvvm.model.MovieModel;
import maplonki.popularmovies.mvvm.util.Constants;
import maplonki.popularmovies.mvvm.view.activity.MainActivity;
import maplonki.popularmovies.mvvm.viewModel.MovieViewModel;

/**
 * Created by hugo on 4/11/16.
 */
public class MovieDetailFragment extends BaseFragment {

    private MovieModel mMovieModel;

    private FragmentMovieDetailBinding detailBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieModel = getArguments().getParcelable(Constants.EXTRAS_KEY_MOVIE);


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

        if (mMovieModel != null) {
            detailBinding = DataBindingUtil.bind(view);
            detailBinding.setMovie(new MovieViewModel(getActivity(), mMovieModel));
        }

        ((MainActivity) getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.detail_toolbar));

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
