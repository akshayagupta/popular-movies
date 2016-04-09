package app.zh.popularmovies.app.models;

public class Review
{

    private String _movieId ;
    private String _id;
    private String _author;
    private String _content;

    public Review(String movieId , String id, String author, String content)
    {
        _movieId = movieId;
        _id = id;
        _author = author;
        _content = content;
    }


    public String getId()
    {
        return _id;
    }

    public String getAuthor()
    {
        return _author;
    }

    public String getContent()
    {
        return _content;
    }
}
