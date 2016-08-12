package maplonki.popularmovies.viper.presenter.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import maplonki.popularmovies.viper.R;
import maplonki.popularmovies.viper.application.MoviesApplication;
import maplonki.popularmovies.viper.common.Constants;
import maplonki.popularmovies.viper.entity.MovieModel;


/**
 * Created by hugo on 8/2/2016.
 */
public class MovieViewModel extends BaseObservable implements Parcelable {

    private MovieModel movie;
    private Context context;

    public MovieViewModel(MovieModel movie) {
        this.movie = movie;
    }

    protected MovieViewModel(Parcel in) {
        movie = in.readParcelable(MovieModel.class.getClassLoader());
    }

    public static final Creator<MovieViewModel> CREATOR = new Creator<MovieViewModel>() {
        @Override
        public MovieViewModel createFromParcel(Parcel in) {
            return new MovieViewModel(in);
        }

        @Override
        public MovieViewModel[] newArray(int size) {
            return new MovieViewModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(movie, i);
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
