package app.zh.popularmovies.app.ui.fragment;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import app.zh.popularmovies.app.*;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.network.FetchMovieTask;
import app.zh.popularmovies.app.ui.activity.MovieDetailsActivity;
import app.zh.popularmovies.app.ui.adapter.ImageAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PopularMovieFragment extends android.support.v4.app.Fragment
{

    private GridView _movieGridView;
    private ImageAdapter _imageAdapter;
    private FetchComplete<Movie> fetchCompleteInterFace = new FetchComplete<Movie>()
    {
        @Override
        public void onFetchComplete(ArrayList<Movie> objectList)
        {
            _imageAdapter.updateData(objectList);
        }
    };

    private String sortLogic;

    public PopularMovieFragment()
    {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        _movieGridView = ((GridView) rootView.findViewById(R.id.movie_grid_view));
        ArrayList<Movie> _movieList = new ArrayList<>();
        _imageAdapter = new ImageAdapter(getActivity(), _movieList);
        _movieGridView.setAdapter(_imageAdapter);
        sortLogic = Utilities.getSortLogic(getActivity());
        if (savedInstanceState == null || !savedInstanceState.containsKey("movieList"))
        {
            fetchMovies();
        } else
        {
            _movieList = savedInstanceState.getParcelableArrayList("movieList");
            _imageAdapter.updateData(_movieList);
        }
        _movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ((CallBack) getActivity()).itemClicked(_imageAdapter.getItem(position));
            }
        });
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (sortLogic != null && sortLogic.equals(getString(R.string.pref_units_favorite)))
        {
            ArrayList<Movie> movieArrayList = DependencyFactory.getFavoriteFeature().getAllFavoriteMovie();
            _imageAdapter.updateData(movieArrayList);
            return;
        }
        if (sortLogic != null && !sortLogic.equals(Utilities.getSortLogic(getActivity())))
        {
            sortLogic = Utilities.getSortLogic(getActivity());
            fetchMovies();
        }
    }

    private void fetchMovies()
    {
        if (sortLogic != null && sortLogic.equals(getString(R.string.pref_units_favorite)))
        {
            ArrayList<Movie> movieArrayList = DependencyFactory.getFavoriteFeature().getAllFavoriteMovie();
            _imageAdapter.updateData(movieArrayList);
            return;
        }
        FetchMovieTask fetchMovieTask = new FetchMovieTask(Utilities.getSortLogic(getActivity()), fetchCompleteInterFace);
        fetchMovieTask.execute();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        if (_imageAdapter.getCount() != 0)
        {
            outState.putParcelableArrayList("movieList", _imageAdapter.getData());
        }
        super.onSaveInstanceState(outState);
    }


    public interface CallBack
    {
        public void itemClicked(Movie movie);
    }
}
