package maplonki.popularmovies.mvvm.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import maplonki.popularmovies.mvvm.R;
import maplonki.popularmovies.mvvm.databinding.CellMovieGridBinding;
import maplonki.popularmovies.mvvm.model.MovieModel;
import maplonki.popularmovies.mvvm.viewModel.MovieViewModel;

/**
 * Created by hugo on 4/5/16.
 * <p/>
 * This is the grid adapter for the MainActivity,
 * which displays the movie posters using a RecyclerView and GridLayoutManager
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieModel> mMovieList;
    private View.OnClickListener mViewListener;

    public MovieGridAdapter(Context mContext, List<MovieModel> mMovieList, View.OnClickListener viewListener) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        this.mViewListener = viewListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CellMovieGridBinding cellBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.cell_movie_grid,
                parent,
                false
        );
        return new ViewHolder(cellBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel model = mMovieList.get(position);
        if (model != null) {
            holder.cellBinding.setMovie(new MovieViewModel(mContext, model));
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CellMovieGridBinding cellBinding;

        public ViewHolder(CellMovieGridBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(mViewListener);
            cellBinding = binding;
        }
    }
}
