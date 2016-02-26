package app.zh.popularmovies.app.convertor;

import app.zh.popularmovies.app.models.Movie;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieConvertor
{
    public Movie getMovieFromJSon(JSONObject jsonObject) throws JSONException
    {
        String title = jsonObject.getString("original_title");
        String overView = jsonObject.getString("overview");
        String posterPath = jsonObject.getString("poster_path");
        String releaseDate = jsonObject.getString("release_date");
        double voteAveraage = jsonObject.getDouble("vote_average");
        if (posterPath != null)
        {
            return new Movie(posterPath, title, overView, releaseDate, voteAveraage);
        } else
        {
            return null;
        }
    }

}
