package app.zh.popularmovies.app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailer implements Parcelable
{
    private String _movieId;
    private String _name;
    private String _key;

    public Trailer(String movieId, String name, String key)
    {
        _movieId = movieId;
        _name = name;
        _key = key;
    }

    public Trailer(Parcel in)
    {
        _movieId = in.readString();
        _name = in.readString();
        _key = in.readString();
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


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_movieId);
        dest.writeString(_name);
        dest.writeString(_key);
    }


    public static final Parcelable.Creator<Trailer> CREATOR = new Parcelable.Creator<Trailer>()
    {
        @Override
        public Trailer createFromParcel(Parcel source)
        {
            return new Trailer(source);
        }

        @Override
        public Trailer[] newArray(int size)
        {
            return new Trailer[size];
        }
    };

}
