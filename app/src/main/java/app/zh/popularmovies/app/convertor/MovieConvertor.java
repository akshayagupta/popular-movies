package app.zh.popularmovies.app.convertor;

import android.util.Log;
import app.zh.popularmovies.app.models.Movie;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieConvertor
{
    public Movie getMovieFromJSon(JSONObject jsonObject) throws JSONException
    {
        Log.d("getmoviejsonstring", jsonObject.toString());
        String movieId = jsonObject.getString("id");
        String title = jsonObject.getString("original_title");
        String overView = jsonObject.getString("overview");
        String posterPath = jsonObject.getString("poster_path");
        String releaseDate = jsonObject.getString("release_date");
        double voteAveraage = jsonObject.getDouble("vote_average");
        boolean hasVideos = jsonObject.getBoolean("video");
        if (posterPath != null)
        {
            return new Movie(movieId, posterPath, title, overView, releaseDate, voteAveraage , hasVideos);
        } else
        {
            return null;
        }
    }

    public Movie getMovieFromJsonString(String jsonString) throws JSONException
    {
        Movie movie;
        try
        {
            JSONObject jsonObject = new JSONObject(jsonString);
            movie = getMovieFromJSon(jsonObject);
        } catch (JSONException jsonException)
        {
            return null;
        }
        return movie;
    }

    public String getMovieJsonString(Movie movie)
    {
        JSONObject result = new JSONObject();
        try
        {
            result.put("id", movie.getMovieId());
            result.put("original_title", movie.getTitle());
            result.put("overview", movie.getOverView());
            result.put("vote_average", movie.get_voteAverage());
            result.put("release_date", movie.getReleaseDate());
            result.put("poster_path", movie.getPosterPath());
            result.put("video" , movie.getHasVideos());
        } catch (JSONException jsonString)
        {
            return null;
        }
        return result.toString();
    }

}
