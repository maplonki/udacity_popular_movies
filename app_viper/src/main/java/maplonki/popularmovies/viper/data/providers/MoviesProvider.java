package maplonki.popularmovies.viper.data.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import maplonki.popularmovies.viper.BuildConfig;


/**
 * Created by hugo on 4/11/16.
 */
public class MoviesProvider extends ContentProvider {

    //com.maplonki.popular_movies
    private static final String PROVIDER_NAME = BuildConfig.APPLICATION_ID + ".movies";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/movies");

    private static final int MOVIES = 1;
    private static final int MOVIES_ID = 2;

    private static final UriMatcher uriMatcher = getUriMatcher();
    private MoviesDatabase moviesDatabase;

    private static UriMatcher getUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "movies", MOVIES);
        uriMatcher.addURI(PROVIDER_NAME, "movies/#", MOVIES_ID);
        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        moviesDatabase = new MoviesDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        if (uriMatcher.match(uri) == MOVIES_ID) {
            id = uri.getPathSegments().get(1);
        }
        return moviesDatabase.getMovies(id, projection, selection, selectionArgs, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MOVIES:
                return "vnd.android.cursor.dir/vnd." + PROVIDER_NAME;
            case MOVIES_ID:
                return "vnd.android.cursor.item/vnd." + PROVIDER_NAME;
        }
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        try {
            long id = moviesDatabase.addMovie(values);
            return ContentUris.withAppendedId(CONTENT_URI, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        String id = null;
        if (uriMatcher.match(uri) == MOVIES_ID) {
            id = uri.getPathSegments().get(1);
        }
        return moviesDatabase.deleteMovie(id);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String id = null;
        if (uriMatcher.match(uri) == MOVIES_ID) {
            id = uri.getPathSegments().get(1);
        }
        return moviesDatabase.updateMovie(id, values);
    }
}
