package app.zh.popularmovies.app.network;

import android.net.Uri;
import android.os.AsyncTask;
import app.zh.popularmovies.app.BuildConfig;
import app.zh.popularmovies.app.FetchComplete;
import app.zh.popularmovies.app.convertor.ReviewConvertor;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.models.Review;
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

public class FetchReviewTask extends AsyncTask<Void, Void, ArrayList<Review>>
{
    Movie _movie;
    private FetchComplete<Review> fetchCompleteInerface ;

    public FetchReviewTask(Movie movie , FetchComplete<Review> reviewFetchComplete)
    {
        _movie = movie;
        fetchCompleteInerface = reviewFetchComplete;
    }

    private ArrayList<Review> getReviewInfo(String jsonData) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(jsonData);
        ArrayList<Review> reviewList = new ReviewConvertor().getReviewList(jsonObject);
        return reviewList;
    }

    @Override
    protected void onPostExecute(ArrayList<Review> reviewList)
    {
        super.onPostExecute(reviewList);
        fetchCompleteInerface.onFetchComplete(reviewList);
    }

    @Override
    protected ArrayList<Review> doInBackground(Void... params)
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
                    .appendPath(_movie.getMovieId()).appendPath("reviews")
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
            return getReviewInfo(movieInfoJsonStr);

        } catch (JSONException jsonException)
        {

        }
        return null;
    }
}
