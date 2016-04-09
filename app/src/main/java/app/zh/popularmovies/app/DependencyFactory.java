package app.zh.popularmovies.app;


import android.content.Context;
import app.zh.popularmovies.app.features.FavoriteFeature;
import app.zh.popularmovies.app.features.IFavoriteFeature;

import java.util.Map;

public class DependencyFactory
{

    private static IFavoriteFeature _favoriteFeature;

    public static void init(Context context)
    {
        _favoriteFeature = new FavoriteFeature(context);
    }

    public static IFavoriteFeature getFavoriteFeature()
    {
        return _favoriteFeature;
    }


}
