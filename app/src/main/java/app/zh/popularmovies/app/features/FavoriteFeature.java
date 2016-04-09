package app.zh.popularmovies.app.features;

import android.content.Context;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;

public class FavoriteFeature implements IFavoriteFeature
{
    private static final String FAV_KEY = "fav";
    private Context _context;
    private MovieConvertor _movieConvertor;
    KeyValueStore _favStore;

    public FavoriteFeature(Context context)
    {
        _context = context;
        _movieConvertor = new MovieConvertor();
        _favStore = new KeyValueStore(_context, FAV_KEY);
    }


    @Override
    public void addToFavorite(Movie movie)
    {
        String movieId = movie.getMovieId();
        String favJson = _movieConvertor.getMovieJsonString(movie);
        _favStore.putString(movieId, favJson);
    }

    @Override
    public void removeFavorite(String movieId)
    {
        _favStore.remove(movieId);
    }

    @Override
    public boolean isFavorite(String movieId)
    {
        if (_favStore.containsKey(movieId))
        {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Movie> getAllFavoriteMovie()
    {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        Map<String, String> allKeysMap = ((Map<String, String>) _favStore.getAllKeys());
        for (Map.Entry<String, String> mapEntry : allKeysMap.entrySet())
        {
            try
            {
                String favJson = mapEntry.getValue();
                movieList.add(_movieConvertor.getMovieFromJsonString(favJson));
            } catch (JSONException jsonException)
            {

            }
        }
        return movieList;
    }
}
