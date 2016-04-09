package app.zh.popularmovies.app;


import android.content.Context;

import java.util.Map;

public class DependencyFactory
{
    private static PersistedMap _configMap;

    public static void init(Context context)
    {
        _configMap = new PersistedMap(context.getSharedPreferences("config", Context.MODE_PRIVATE), "config_properties");
    }

    public static Map<String, String> getPropertiesMap()
    {
        return _configMap;
    }


}
