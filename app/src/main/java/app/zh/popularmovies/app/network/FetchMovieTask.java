package app.zh.popularmovies.app.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import app.zh.popularmovies.app.BuildConfig;
import app.zh.popularmovies.app.Utilities;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.ui.adapter.ImageAdapter;
import app.zh.popularmovies.app.ui.fragment.PopularMovieFragment;
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

public class FetchMovieTask extends AsyncTask<Void, Void, ArrayList<Movie>>
{

    private ImageAdapter _imageAdapter;
    private String _sortLogic;

    public FetchMovieTask(String sortLogic, ImageAdapter imageAdapter)
    {
        _imageAdapter = imageAdapter;
        _sortLogic = sortLogic;
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... params)
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


            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_MOVIE_API_KEY)
                    .appendQueryParameter(SORTING_PARAM, _sortLogic)
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


    private ArrayList<Movie> getMovieInfoFromJSon(String jsonData) throws JSONException
    {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
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


    @Override
    protected void onPostExecute(ArrayList<Movie> movies)
    {

        if (movies != null)
        {
            _imageAdapter.updateData(movies);
        }
    }
}
