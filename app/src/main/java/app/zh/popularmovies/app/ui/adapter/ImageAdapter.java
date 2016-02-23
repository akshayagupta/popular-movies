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

import java.util.List;

public class ImageAdapter extends BaseAdapter
{
    private Context _context;
    private List<Movie> _movieList;

    public ImageAdapter(Context context, List<Movie> movieList)
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
        ImageView imageView = new ImageView(_context);
        Log.d("imageUrl", item.get_posterPath());
        Picasso.with(_context).load(item.get_posterPath()).into(imageView);
        return imageView;
    }

    public void updateData(List<Movie> movieList)
    {
        _movieList = movieList;
        notifyDataSetChanged();
    }
}
