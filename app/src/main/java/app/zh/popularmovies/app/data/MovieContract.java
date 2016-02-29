package app.zh.popularmovies.app.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract
{
    public static final String CONTENT_AUTHORITY = "app.zh.popularmovies.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_TRAILER = "trailer";
    public static final String PATH_REVIEW = "review";

    public static final class MovieEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "movie";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        public static final String COLUMN_MOVIE_POSTER_PATH = "poster_path";

        public static final String COLUMN_MOVIE_OVERVIEW = "overview";

        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";

        public static final String COLUMN_MOVIE_TITLE = "title";

        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        public static final String COLUMN_IS_FAVORITE = "favorite";
    }

    public static final class TrailerEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "trailer";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRAILER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRAILER;
        public static final String COLUMN_MOVIE_TRAILER_PATH = "trailer_path";
        public static final String COLUMN_MOVIE_KEY = "movie_key";

    }

    public static final class ReviewEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "review";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;
        public static final String COLUMN_MOVIE_REVIEW = "review";
        public static final String COLUMN_MOVIE_KEY = "movie_key";

    }


}
