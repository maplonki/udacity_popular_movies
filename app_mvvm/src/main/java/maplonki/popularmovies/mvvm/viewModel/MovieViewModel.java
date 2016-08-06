package maplonki.popularmovies.mvvm.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import maplonki.popularmovies.mvvm.R;
import maplonki.popularmovies.mvvm.model.MovieModel;
import maplonki.popularmovies.mvvm.util.Constants;

/**
 * Created by hugo on 8/2/2016.
 */
public class MovieViewModel extends BaseObservable {

    private Context context;
    private MovieModel movie;

    public MovieViewModel(Context context, MovieModel movie) {
        this.movie = movie;
        this.context = context;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getOverview() {
        return movie.getOverview();
    }

    public String getReleaseDate() {
        String formattedDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(movie.getReleaseDate());
        return String.format(context.getString(R.string.release_date_format), formattedDate);
    }

    public String getRating() {
        return String.format(context.getString(R.string.rating_format), (int) movie.getVoteAverage());
    }

    public String getBackdrop() {
        return movie.getBackdropPath();
    }

    public String getPortrait() {
        return movie.getPosterPath();
    }

    @BindingAdapter({"bind:movieImage"})
    public static void setMovieImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(Constants.BASE_IMAGE_URL + imageUrl)
                .into(view);
    }
}
