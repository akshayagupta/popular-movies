package app.zh.popularmovies.app.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Movie implements Parcelable
{


    private String _posterPath;
    private String _overView;
    private String _releaseDate;
    private String _title;
    private double _voteAverage;
    private List<String> _videoKeyList;

    public Movie(String posterPath, String title, String overView, String releaseDate, double voteAverage, List<String> videoLinkList)
    {
        _posterPath = posterPath;
        _title = title;
        _overView = overView;
        _releaseDate = releaseDate;
        _voteAverage = voteAverage;
        _videoKeyList = videoLinkList;
    }

    private Movie(Parcel in)
    {
        _posterPath = in.readString();
        _title = in.readString();
        _overView = in.readString();
        _releaseDate = in.readString();
        _voteAverage = in.readDouble();
    }

    public String getPosterPath()
    {
        return _posterPath;
    }


    public String getOverView()
    {
        return _overView;
    }

    public String getReleaseDate()
    {
        return _releaseDate;
    }

    public String getTitle()
    {
        return _title;
    }


    public double get_voteAverage()
    {
        return _voteAverage;
    }

    public List<String> getVideoKeyList()
    {
        return _videoKeyList;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(_posterPath);
        dest.writeString(_title);
        dest.writeString(_overView);
        dest.writeString(_releaseDate);
        dest.writeDouble(_voteAverage);

    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>()
    {
        @Override
        public Movie createFromParcel(Parcel source)
        {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size)
        {
            return new Movie[size];
        }
    };
}
