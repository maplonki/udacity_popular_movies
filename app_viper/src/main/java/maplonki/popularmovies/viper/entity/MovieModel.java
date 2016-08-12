package maplonki.popularmovies.viper.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by hugo on 4/5/16.
 * <p/>
 * This class represents the movie
 * and has all of the related attribuets
 */
public class MovieModel implements Parcelable {
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private String overview;
    @SerializedName("release_date")
    private Date releaseDate;
    @SerializedName("vote_average")
    private float voteAverage;

    protected MovieModel(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = new Date(in.readLong());
        voteAverage = in.readFloat();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeString(overview);
        dest.writeLong(releaseDate.getTime());
        dest.writeFloat(voteAverage);
    }
}
