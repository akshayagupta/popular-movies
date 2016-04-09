package app.zh.popularmovies.app.models;

public class Trailer
{
    private String _movieId;
    private String _name ;
    private String _key ;

    public Trailer(String movieId , String name , String key){
        _movieId = movieId ;
        _name = name ;
        _key = key ;
    }

    public String getMovieId()
    {
        return _movieId;
    }

    public String getName()
    {
        return _name;
    }

    public String getKey()
    {
        return _key;
    }



}
