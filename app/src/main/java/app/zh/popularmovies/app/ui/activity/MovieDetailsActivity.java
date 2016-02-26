package app.zh.popularmovies.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import app.zh.popularmovies.app.R;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends ActionBarActivity
{
    public static String MOVIE_OVERVIEW = "overview";
    public static String MOVIE_TITLE = "details";
    public static String MOVIE_RELEASE_DATE = "release_date";
    public static String MOVIE_VOTE_AVERAGE = "vote_average";
    public static String MOVIE_POSTER_URL = "movie_poster_url";
    String title, description, releaseDate, posterURL;
    double voteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        extractDataFromIntent();
        initView();
    }

    private void extractDataFromIntent()
    {
        title = getIntent().getStringExtra(MOVIE_TITLE);
        description = getIntent().getStringExtra(MOVIE_OVERVIEW);
        releaseDate = getIntent().getStringExtra(MOVIE_RELEASE_DATE);
        voteAverage = getIntent().getDoubleExtra(MOVIE_VOTE_AVERAGE, 1);
        posterURL = getIntent().getStringExtra(MOVIE_POSTER_URL);
    }

    private void initView()
    {
        TextView titleView = ((TextView) findViewById(R.id.title));
        TextView overView = ((TextView) findViewById(R.id.overview));
        TextView releaseDateView = ((TextView) findViewById(R.id.release_date));
        TextView voteAvgView = ((TextView) findViewById(R.id.vote_avg));
        ImageView posterView = ((ImageView) findViewById(R.id.poster));
        titleView.setText(title);
        releaseDateView.setText("Release Date:  " + releaseDate);
        voteAvgView.setText("Rating:  " + voteAverage);
        overView.setText("OverView:  " + description);
        Picasso.with(this).load(posterURL).into(posterView);
    }


}
