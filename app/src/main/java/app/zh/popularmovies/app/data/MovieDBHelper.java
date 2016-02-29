package app.zh.popularmovies.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "weather.db";


    public MovieDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        createMovieTable(db);
        createTrailerTable(db);
        createReviewTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    private void createMovieTable(SQLiteDatabase db)
    {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY," +
                MovieContract.MovieEntry.COLUMN_MOVIE_TITLE + " TEXT UNIQUE NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE + " REAL NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_IS_FAVORITE + " BOOLEAN NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL " +
                " );";
        ;
        db.execSQL(SQL_CREATE_MOVIE_TABLE);

    }

    private void createTrailerTable(SQLiteDatabase db)
    {
        final String SQL_CREATE_TRAILER_TABLE = "CREATE TABLE " + MovieContract.TrailerEntry.TABLE_NAME + " (" +
                MovieContract.TrailerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MovieContract.TrailerEntry.COLUMN_MOVIE_KEY + " INTEGER NOT NULL, " +
                MovieContract.TrailerEntry.COLUMN_MOVIE_TRAILER_PATH + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + MovieContract.TrailerEntry.COLUMN_MOVIE_KEY + ") REFERENCES " +
                MovieContract.MovieEntry.TABLE_NAME + " (" + MovieContract.MovieEntry._ID + ")";
        db.execSQL(SQL_CREATE_TRAILER_TABLE);
    }

    private void createReviewTable(SQLiteDatabase db)
    {
        final String SQL_CREATE_REVIEW_TABLE = "CREATE TABLE " + MovieContract.ReviewEntry.TABLE_NAME + " (" +
                MovieContract.ReviewEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieContract.ReviewEntry.COLUMN_MOVIE_KEY + " INTEGER NOT NULL, " +
                MovieContract.ReviewEntry.COLUMN_MOVIE_REVIEW + " TEXT NOT NULL, " +
                " FOREIGN KEY (" + MovieContract.ReviewEntry.COLUMN_MOVIE_KEY + ") REFERENCES " +
                MovieContract.MovieEntry.TABLE_NAME + " (" + MovieContract.MovieEntry._ID + ")";
        db.execSQL(SQL_CREATE_REVIEW_TABLE);
    }
}
