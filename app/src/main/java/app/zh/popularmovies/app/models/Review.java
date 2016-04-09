package app.zh.popularmovies.app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable
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


    private Review(Parcel in)
    {
        _movieId = in.readString();
        _id = in.readString();
        _author = in.readString();
        _content = in.readString();
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_movieId);
        dest.writeString(_id);
        dest.writeString(_author);
        dest.writeString(_content);
    }


    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>()
    {
        @Override
        public Review createFromParcel(Parcel source)
        {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size)
        {
            return new Review[size];
        }
    };
}
