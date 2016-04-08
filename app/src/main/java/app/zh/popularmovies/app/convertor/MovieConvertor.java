package app.zh.popularmovies.app.convertor;

import app.zh.popularmovies.app.models.Movie;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieConvertor
{
    public Movie getMovieFromJSon(JSONObject jsonObject) throws JSONException
    {
        String title = jsonObject.getString("original_title");
        String overView = jsonObject.getString("overview");
        String posterPath = jsonObject.getString("poster_path");
        String releaseDate = jsonObject.getString("release_date");
        double voteAveraage = jsonObject.getDouble("vote_average");
        List<String> videoLinkList = new VideoConvertor().getVideoKey(jsonObject);
        if (posterPath != null)
        {
            return new Movie(posterPath, title, overView, releaseDate, voteAveraage, videoLinkList);
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
            result.put("original_title", movie.getTitle());
            result.put("overview", movie.getOverView());
            result.put("vote_average", movie.get_voteAverage());
            result.put("release_date", movie.getReleaseDate());
            result.put("poster_path", movie.getPosterPath());
            result.put("results", new VideoConvertor().getVideoJson(movie));
        } catch (JSONException jsonString)
        {
            return null;
        }
        return result.toString();
    }

}
