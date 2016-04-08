package com.maplonki.popular_movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maplonki.popular_movies.MConstants;
import com.maplonki.popular_movies.R;
import com.maplonki.popular_movies.models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

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
        View parentView = View.inflate(mContext, R.layout.cell_movie_grid, null);
        return new ViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel model = mMovieList.get(position);

        Picasso.with(mContext)
                .load(MConstants.BASE_IMAGE_URL + model.getPosterPath())
                .into(holder.imageMovie);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(mViewListener);
            imageMovie = (ImageView) itemView;
        }
    }
}
