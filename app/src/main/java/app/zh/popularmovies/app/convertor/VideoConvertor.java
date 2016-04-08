package app.zh.popularmovies.app.convertor;

import app.zh.popularmovies.app.models.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoConvertor
{
    public List<String> getVideoKey(JSONObject jsonObject)
    {
        List<String> trailerList = new ArrayList<String>();
        try
        {
            JSONArray reviewResult = jsonObject.getJSONArray("results");
            for (int i = 0; i < reviewResult.length(); i++)
            {
                JSONObject trailerItem = reviewResult.getJSONObject(i);
                String key = trailerItem.getString("key");
                trailerList.add(key);
            }
            return trailerList;

        } catch (JSONException jsonException)
        {

        }
        return trailerList;
    }

    public JSONArray getVideoJson(Movie movie)
    {
        List<String> keyList = movie.getVideoKeyList();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < keyList.size(); i++)
        {
            try
            {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("key", keyList.get(i));
                jsonArray.put(i, jsonObject);
            } catch (JSONException jsonException)
            {
                return null;
            }
        }
        return jsonArray ;
    }
}
