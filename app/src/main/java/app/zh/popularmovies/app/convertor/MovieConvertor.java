package app.zh.popularmovies.app.convertor;

import app.zh.popularmovies.app.models.Movie;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieConvertor
{
    public Movie getMovieFromJSon(JSONObject jsonObject) throws JSONException
    {
        int id = jsonObject.getInt("id");
        String title = jsonObject.getString("original_title");
        String overView = jsonObject.getString("overview");
        String language = jsonObject.getString("original_language");
        String posterPath = "";
        String releaseDate = jsonObject.getString("release_date");
        double voteAveraage = jsonObject.getDouble("vote_average");
        if (jsonObject.getString("poster_path") != null)
        {
            posterPath = jsonObject.getString("poster_path");
            return new Movie(id, posterPath, title, overView, releaseDate, voteAveraage);
        } else
        {
            return null;
        }
    }

}
