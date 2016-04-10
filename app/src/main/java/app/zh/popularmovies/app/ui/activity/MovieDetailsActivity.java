package app.zh.popularmovies.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.ui.fragment.MovieDetailsFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends ActionBarActivity
{
    public static String JSON_DESCRIPTION = "json_description";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            Movie movie = getIntent().getExtras().getParcelable(JSON_DESCRIPTION);
            arguments.putParcelable(MovieDetailsFragment.DETAIL_URI , movie);
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
