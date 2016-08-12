package maplonki.popularmovies.viper.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import maplonki.popularmovies.viper.databinding.CellMovieGridBinding;
import maplonki.popularmovies.viper.R;
import maplonki.popularmovies.viper.presenter.viewmodel.MovieViewModel;

/**
 * Created by hugo on 4/5/16.
 * <p/>
 * This is the grid adapter for the MainActivity,
 * which displays the movie posters using a RecyclerView and GridLayoutManager
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieViewModel> mMovieList;
    private View.OnClickListener mViewListener;

    public MovieGridAdapter(Context mContext, List<MovieViewModel> mMovieList, View.OnClickListener viewListener) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        this.mViewListener = viewListener;
    }

    public MovieGridAdapter(Context mContext, View.OnClickListener mViewListener) {
        this(mContext,new ArrayList<MovieViewModel>() ,mViewListener);
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
        MovieViewModel model = mMovieList.get(position);
        if (model != null) {
            model.setContext(mContext);
            holder.cellBinding.setMovie(model);
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public MovieViewModel getItem(int position) {
        return mMovieList.get(position);
    }

    public void setObjectList(List<MovieViewModel> list){
        this.mMovieList = list;
        notifyDataSetChanged();
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
