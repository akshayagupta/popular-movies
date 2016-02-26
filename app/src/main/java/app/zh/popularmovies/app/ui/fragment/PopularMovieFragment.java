package app.zh.popularmovies.app.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import app.zh.popularmovies.app.BuildConfig;
import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
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

    public PopularMovieFragment()
    {
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        _movieGridView = ((GridView) rootView.findViewById(R.id.movie_grid_view));
        List<Movie> movieList = new ArrayList<>();
        _imageAdapter = new ImageAdapter(getActivity(), movieList);
        _movieGridView.setAdapter(_imageAdapter);
        _movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Context context = view.getContext();
                Movie itemClicked = _imageAdapter.getItem(position);
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra(MovieDetailsActivity.MOVIE_OVERVIEW, itemClicked.getOverView());
                intent.putExtra(MovieDetailsActivity.MOVIE_TITLE, itemClicked.getTitle());
                intent.putExtra(MovieDetailsActivity.MOVIE_RELEASE_DATE, itemClicked.getReleaseDate());
                intent.putExtra(MovieDetailsActivity.MOVIE_VOTE_AVERAGE, itemClicked.get_voteAverage());
                intent.putExtra(MovieDetailsActivity.MOVIE_POSTER_URL, itemClicked.getPosterPath());
                context.startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        updateView();
    }

    private void updateView()
    {
        FetchMovieTask fetchMovieTask = new FetchMovieTask();
        fetchMovieTask.execute();
    }

    public class FetchMovieTask extends AsyncTask<Void, Void, List<Movie>>
    {
        @Override
        protected List<Movie> doInBackground(Void... params)
        {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieInfoJsonStr = null;

            try
            {
                final String MOVIE_BASE_URL =
                        "http://api.themoviedb.org/3/discover/movie";
                final String APPID_PARAM = "api_key";
                final String SORTING_PARAM = "sort_by";
                SharedPreferences sharedPrefs =
                        PreferenceManager.getDefaultSharedPreferences(getActivity());
                String unitType = sharedPrefs.getString(
                        getString(R.string.pref_units_key),
                        getString(R.string.pref_units_most_popular));

                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_MOVIE_API_KEY)
                        .appendQueryParameter(SORTING_PARAM, unitType)
                        .build();

                URL url = new URL(builtUri.toString());
                Log.d("moviefragment", builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null)
                {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0)
                {
                    return null;
                }
                movieInfoJsonStr = buffer.toString();
            } catch (IOException e)
            {
                return null;
            } finally
            {
                if (urlConnection != null)
                {
                    urlConnection.disconnect();
                }
                if (reader != null)
                {
                    try
                    {
                        reader.close();
                    } catch (final IOException e)
                    {

                    }
                }
            }

            try
            {
                return getMovieInfoFromJSon(movieInfoJsonStr);

            } catch (JSONException jsonException)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movieList)
        {
            if (movieList != null)
            {
                Log.d("moviefragment", "" + movieList.size());
                _imageAdapter.updateData(movieList);
            }
        }

        private List<Movie> getMovieInfoFromJSon(String jsonData) throws JSONException
        {
            List<Movie> movieList = new ArrayList<Movie>();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray movieJsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < movieJsonArray.length(); i++)
            {
                Movie movie = new MovieConvertor().getMovieFromJSon(movieJsonArray.getJSONObject(i));
                if (movie != null)
                {
                    movieList.add(movie);
                }
            }
            return movieList;
        }


    }
}
