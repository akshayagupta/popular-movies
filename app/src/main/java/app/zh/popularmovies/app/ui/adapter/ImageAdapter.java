package app.zh.popularmovies.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import app.zh.popularmovies.app.R;

public class ImageAdapter extends BaseAdapter
{
    private Context _context;

    public ImageAdapter(Context context)
    {
        _context = context;
    }

    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (convertView == null)
        {
            view = LayoutInflater.from(_context).inflate(R.layout.grid_view_movie_item, null);

        }
        return null;
    }
}
