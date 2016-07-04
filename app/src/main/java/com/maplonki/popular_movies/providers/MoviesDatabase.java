package com.maplonki.popular_movies.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by hugo on 4/11/16.
 */
public class MoviesDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies.db";
    private static final String TABLE_NAME = "movies_table";
    private static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
            "(_id INTEGER PRIMARY KEY, " +
            "TITLE TEXT," +
            "POSTER_URL TEXT," +
            "COVER_URL TEXT," +
            "SYNOPSIS TEXT," +
            "FAVORITE INTEGER," +
            ")";

    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MoviesDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        onCreate(db);
    }

    public Cursor getMovies(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TABLE_NAME);
        if (id != null) {
            sqLiteQueryBuilder.appendWhere("_id" + " = " + id);
        }

        if (sortOrder == null || sortOrder.equals("")) {
            sortOrder = "TITLE";
        }

        return sqLiteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    public long addMovie(ContentValues contentValues) {
        long result = getWritableDatabase().insert(TABLE_NAME, "", contentValues);
        if (result <= 0) {
            throw new SQLException("Failed to add a movie");
        }
        return result;
    }

    public int deleteMovie(String id) {
        if (id == null) {
            return getWritableDatabase().delete(TABLE_NAME, null, null);
        } else {
            return getWritableDatabase().delete(TABLE_NAME, "_id=?", new String[]{id});
        }
    }

    public int updateMovie(String id, ContentValues contentValues) {
        if (id == null) {
            return getWritableDatabase().update(TABLE_NAME, contentValues, null, null);
        } else {
            return getWritableDatabase().update(TABLE_NAME, contentValues, "_id=?", new String[]{id});
        }
    }

}
