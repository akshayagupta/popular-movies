package app.zh.popularmovies.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.ui.fragment.MovieDetailsFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends ActionBarActivity
{

    public static String MOVIE_OVERVIEW = "overview";
    public static String MOVIE_TITLE = "details";
    public static String MOVIE_RELEASE_DATE = "release_date";
    public static String MOVIE_VOTE_AVERAGE = "vote_average";
    public static String MOVIE_POSTER_URL = "movie_poster_url";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        if (savedInstanceState == null)
        {
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailsFragment.DETAIL_URI, getIntent().getData());

            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }
}
