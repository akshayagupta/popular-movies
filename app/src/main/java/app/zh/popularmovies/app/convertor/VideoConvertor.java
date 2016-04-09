package app.zh.popularmovies.app.convertor;

import android.provider.MediaStore;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.models.Trailer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoConvertor
{
    public ArrayList<Trailer> getVideoKey( JSONObject jsonObject)
    {

        ArrayList<Trailer> trailerList = new ArrayList<Trailer>();
        try
        {
            String movieID = jsonObject.getString("id");
            JSONArray reviewResult = jsonObject.getJSONArray("results");
            for (int i = 0; i < reviewResult.length(); i++)
            {
                JSONObject trailerItem = reviewResult.getJSONObject(i);
                String key = trailerItem.getString("key");
                String name = trailerItem.getString("name");
                trailerList.add(new Trailer(movieID , name , key));
            }
            return trailerList;

        } catch (JSONException jsonException)
        {

        }
        return trailerList;
    }

   }
