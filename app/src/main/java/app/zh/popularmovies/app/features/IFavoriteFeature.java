package app.zh.popularmovies.app.features;

import app.zh.popularmovies.app.models.Movie;

import java.util.ArrayList;

public interface IFavoriteFeature
{
    void addToFavorite(Movie movie);

    void removeFavorite(String movieId);

    boolean isFavorite(String movieId);

    ArrayList<Movie> getAllFavoriteMovie();
}
