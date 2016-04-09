package app.zh.popularmovies.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshayagupta on 9/4/16.
 */
public interface FetchComplete<T>
{
    public void onFetchComplete(ArrayList<T> objectList);
}
