package app.zh.popularmovies.app.convertor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoConvertor
{
    public List<String> getVideoUrl(JSONObject jsonObject)
    {
        List<String> trailerList = new ArrayList<String>();
        try
        {
            JSONArray reviewResult = jsonObject.getJSONArray("results");
            for (int i = 0; i < reviewResult.length(); i++)
            {
                JSONObject trailerItem = reviewResult.getJSONObject(i);
                String key = trailerItem.getString("key");
                trailerList.add("https://www.youtube.com/watch?v=" + key);
            }
            return trailerList;

        } catch (JSONException jsonException)
        {

        }
        return trailerList;
    }
}
