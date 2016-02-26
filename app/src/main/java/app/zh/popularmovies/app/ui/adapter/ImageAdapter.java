package app.zh.popularmovies.app.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Movie> _movieList;

    public ImageAdapter(Context context, ArrayList<Movie> movieList)
    {
        _context = context;
        _movieList = movieList;
    }

    @Override
    public int getCount()
    {
        return _movieList.size();
    }

    @Override
    public Movie getItem(int position)
    {
        return _movieList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Movie item = getItem(position);
        Log.d("moviefragment", item.getPosterPath());
        View view = convertView;
        if (convertView == null)
        {
            view = LayoutInflater.from(_context).inflate(R.layout.grid_view_movie_item, null);
        }
        ImageView imageView = ((ImageView) view.findViewById(R.id.movie_image));
        Picasso.with(_context).load(item.getPosterPath()).into(imageView);
        return imageView;
    }

    public void updateData(ArrayList<Movie> movieList)
    {
        _movieList = movieList;
        notifyDataSetChanged();
    }
}
