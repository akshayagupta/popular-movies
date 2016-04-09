package app.zh.popularmovies.app;

import android.content.SharedPreferences;
import org.json.JSONObject;
import java.util.*;


public class PersistedMap implements Map<String, String>
{
    private final SharedPreferences _store;
    private final String _key;
    private JSONObject _json;

    public PersistedMap(SharedPreferences store, String key)
    {
        _store = store;
        _key = key;
        try

        {
            _json = new JSONObject(_store.getString(key, ""));
        } catch (Exception e)
        {
            _json = new JSONObject();
        }
    }

    @Override
    public void clear()
    {
        _json = new JSONObject();
        SharedPreferences.Editor editor = _store.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean containsKey(Object key)
    {
        return _json.has((String) key);
    }

    @Override
    public boolean containsValue(Object value)
    {
        return values().contains(value);
    }

    @Override
    public Set<Entry<String, String>> entrySet()
    {
        Set<Entry<String, String>> entries = new HashSet<Entry<String, String>>();
        for (String s : keySet())
        {
            entries.add(new PersistedEntry(s, get(s)));
        }
        return entries;
    }

    @Override
    public String get(Object key)
    {
        return _json.optString((String) key, null);
    }

    @Override
    public boolean isEmpty()
    {
        return _json.length() == 0;
    }

    @Override
    public Set<String> keySet()
    {
        Set<String> keySet = new HashSet<String>();
        Iterator<String> iterator = (Iterator<String>) _json.keys();
        while (iterator.hasNext())
        {
            keySet.add(iterator.next());
        }
        return keySet;
    }

    @Override
    public String put(String key, String value)
    {
        String previousValue = _json.optString(key, null);
        try
        {
            _json.put(key, value);
            writeBack();
            return previousValue;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map)
    {
        for (String s : map.keySet())
        {
            put(s, map.get(s));
        }
    }

    @Override
    public String remove(Object key)
    {
        String returnValue = (String) _json.remove((String) key);
        try
        {
            writeBack();
            return returnValue;
        } catch (RuntimeException e)
        {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int size()
    {
        return _json.length();
    }

    @Override
    public Collection<String> values()
    {
        Set<String> set = new HashSet<String>();
        for (Entry<String, String> entry : entrySet())
        {
            set.add(entry.getValue());
        }
        return set;
    }

    private void writeBack()
    {
        SharedPreferences.Editor editor = _store.edit();
        editor.putString(_key, _json.toString());
        editor.apply();
    }

    private class WriteFailedException extends Exception
    {
    }

    private class PersistedEntry implements Entry<String, String>
    {
        String _key, _value;

        public PersistedEntry(String key, String value)
        {
            super();
            _key = key;
            _value = value;
        }

        @Override
        public String getKey()
        {
            return _key;
        }

        @Override
        public String getValue()
        {
            return _value;
        }

        @Override
        public String setValue(String object)
        {
            _value = object;
            return _value;
        }
    }
}
