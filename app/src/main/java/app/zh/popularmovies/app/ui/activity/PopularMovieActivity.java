package app.zh.popularmovies.app.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.ui.fragment.MovieDetailsFragment;
import app.zh.popularmovies.app.ui.fragment.PopularMovieFragment;

public class PopularMovieActivity extends ActionBarActivity implements PopularMovieFragment.CallBack
{
    private static final String MOVIE_DETAIL_FRAGMENT_TAG = "MDFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new MovieDetailsFragment(), MOVIE_DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }

        PopularMovieFragment forecastFragment =  ((PopularMovieFragment)getSupportFragmentManager()
                .findFragmentById(R.id.movie_list_fragment));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(Movie movie)
    {
        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putString(MovieDetailsFragment.DETAIL_URI, new MovieConvertor().getMovieJsonString(movie));

            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, MOVIE_DETAIL_FRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, MovieDetailsActivity.class)
                    .putExtra(MovieDetailsActivity.JSON_DESCRIPTION , new MovieConvertor().getMovieJsonString(movie));
            startActivity(intent);
        }

    }
}
