package app.zh.popularmovies.app;

import android.app.Application;

/**
 * Created by akshayagupta on 9/4/16.
 */
public class PopularMovieApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        DependencyFactory.init(this);
    }
}
