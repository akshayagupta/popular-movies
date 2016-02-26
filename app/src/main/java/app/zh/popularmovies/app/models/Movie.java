package app.zh.popularmovies.app.models;

import java.util.List;

public class Movie
{


    private String _posterPath;
    private boolean _adult;
    private String _overView;
    private String _releaseDate;
    private int _movieId;
    private String _title;
    private String _backDropPath;
    private float _popularity;
    private int _voteCount;
    private boolean _videoAvailable;
    private List<Integer> genreIdList;
    private double _voteAverage;
    private String _language;

    public Movie(int movieId, String posterPath, String title, String overView, String releaseDate, double voteAverage)
    {
        _movieId = movieId;
        _posterPath = "https://image.tmdb.org/t/p/w185" + posterPath;
        _title = title;
        _overView = overView;
        _releaseDate = releaseDate;
        _voteAverage = voteAverage;
    }

    public String getPosterPath()
    {
        return _posterPath;
    }

    public boolean isAdult()
    {
        return _adult;
    }

    public String getOverView()
    {
        return _overView;
    }

    public String getReleaseDate()
    {
        return _releaseDate;
    }

    public int get_movieId()
    {
        return _movieId;
    }

    public String getTitle()
    {
        return _title;
    }

    public String get_backDropPath()
    {
        return _backDropPath;
    }

    public float get_popularity()
    {
        return _popularity;
    }

    public int get_voteCount()
    {
        return _voteCount;
    }

    public boolean is_videoAvailable()
    {
        return _videoAvailable;
    }

    public List<Integer> getGenreIdList()
    {
        return genreIdList;
    }

    public double get_voteAverage()
    {
        return _voteAverage;
    }

}
