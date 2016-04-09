package app.zh.popularmovies.app.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import app.zh.popularmovies.app.BuildConfig;
import app.zh.popularmovies.app.FetchComplete;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.convertor.VideoConvertor;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.models.Trailer;
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

public class FetchVideoTask extends AsyncTask<String, Void, ArrayList<Trailer>>
{
    Movie _movie;
    FetchComplete<Trailer> trailerFetchComplete;

    public FetchVideoTask(Movie movie, FetchComplete<Trailer> trailerFetchInterFace)
    {
        _movie = movie;
        trailerFetchComplete = trailerFetchInterFace;
    }

    @Override
    protected ArrayList<Trailer> doInBackground(String... params)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieInfoJsonStr = null;
        try
        {
            final String MOVIE_BASE_URL =
                    "http://api.themoviedb.org/3/movie";
            final String APPID_PARAM = "api_key";
            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendPath(_movie.getMovieId()).appendPath("videos")
                    .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_MOVIE_API_KEY)
                    .build();
            URL url = new URL(builtUri.toString());
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
        } catch (IOException ioException)
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
            return getMovieVideoInfoFromJson(movieInfoJsonStr);

        } catch (JSONException jsonException)
        {

        }
        return null;
    }


    private ArrayList<Trailer> getMovieVideoInfoFromJson(String jsonData) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(jsonData);
        ArrayList<Trailer> trailer = new VideoConvertor().getVideoKey(jsonObject);
        return trailer;
    }

    @Override
    protected void onPostExecute(ArrayList<Trailer> trailerList)
    {
        super.onPostExecute(trailerList);
        trailerFetchComplete.onFetchComplete(trailerList);
    }
}
