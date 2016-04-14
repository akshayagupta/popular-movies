package app.zh.popularmovies.app.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.Utilities;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.ui.fragment.MovieDetailsFragment;
import app.zh.popularmovies.app.ui.fragment.PopularMovieFragment;

public class PopularMovieActivity extends ActionBarActivity implements PopularMovieFragment.CallBack
{
    private static final String MOVIE_DETAIL_FRAGMENT_TAG = "MDFTAG";
    private boolean mTwoPane;
    private String mSortLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mSortLogic = Utilities.getSortLogic(this);
        if (findViewById(R.id.movie_detail_container) != null)
        {
            mTwoPane = true;
            if (savedInstanceState == null)
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new MovieDetailsFragment(), MOVIE_DETAIL_FRAGMENT_TAG)
                        .commit();
            }
        } else
        {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        String sortLogic = Utilities.getSortLogic(this);
        // update the location in our second pane using the fragment manager
        if (sortLogic != null && !sortLogic.equals(mSortLogic))
        {
            PopularMovieFragment ff = (PopularMovieFragment) getSupportFragmentManager().findFragmentById(R.id.movie_list_fragment);
            if (null != ff)
            {
                ff.onSettingChanged(sortLogic);
            }
            MovieDetailsFragment df = (MovieDetailsFragment) getSupportFragmentManager().findFragmentByTag(MOVIE_DETAIL_FRAGMENT_TAG);
            if (null != df)
            {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new MovieDetailsFragment(), MOVIE_DETAIL_FRAGMENT_TAG)
                        .commit();
            }
            mSortLogic = sortLogic;
        } else if (sortLogic != null && sortLogic.equals(getString(R.string.pref_units_favorite)))
        {
            PopularMovieFragment ff = (PopularMovieFragment) getSupportFragmentManager().findFragmentById(R.id.movie_list_fragment);
            if (null != ff)
            {
                ff.onSettingChanged(sortLogic);
            }
        }

    }

    @Override
    public void itemClicked(Movie movie)
    {
        if (mTwoPane)
        {
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailsFragment.DETAIL_URI, movie);
            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, MOVIE_DETAIL_FRAGMENT_TAG)
                    .commit();
        } else
        {
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsActivity.JSON_DESCRIPTION, movie);
            startActivity(intent);
        }

    }
}
