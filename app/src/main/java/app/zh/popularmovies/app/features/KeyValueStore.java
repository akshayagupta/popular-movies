package app.zh.popularmovies.app.features;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class KeyValueStore
{

    private final Context _context;
    private final SharedPreferences.Editor editor;
    private final SharedPreferences sharedPreferences;

    public KeyValueStore(Context context, String prefKey)
    {
        this._context = context;
        sharedPreferences = context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean containsKey(String key)
    {
        return sharedPreferences.contains(key);
    }

    public void putString(String key, String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    public void putInt(String key, int value)
    {
        editor.putInt(key, value);
        editor.commit();

    }

    public void putBoolean(String key, boolean value)
    {
        editor.putBoolean(key, value);
        editor.commit();

    }

    public void putFloat(String key, float value)
    {
        editor.putFloat(key, value);
        editor.commit();

    }

    public void putLong(String key, long value)
    {
        editor.putLong(key, value);
        editor.commit();
    }


    public void remove(String key)
    {
        editor.remove(key);
        editor.commit();

    }

    public void removeAll()
    {
        editor.clear();
        editor.commit();
    }

    public String getString(String key, String defaultValue)
    {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue)
    {

        return sharedPreferences.getInt(key, defaultValue);
    }

    public float getFloat(String key, int defaultValue)
    {

        return sharedPreferences.getFloat(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue)
    {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public long getLong(String key, long defaultValue)
    {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public Map<String, ?> getAllKeys()
    {
        return sharedPreferences.getAll();
    }


}

