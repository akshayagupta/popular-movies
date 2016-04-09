package app.zh.popularmovies.app.convertor;

import app.zh.popularmovies.app.models.Review;
import app.zh.popularmovies.app.models.Trailer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReviewConvertor
{

    public ArrayList<Review> getReviewList(JSONObject jsonObject)
    {

        ArrayList<Review> reviewList = new ArrayList<Review>();
        try
        {
            String movieID = jsonObject.getString("id");
            JSONArray reviewResult = jsonObject.getJSONArray("results");
            for (int i = 0; i < reviewResult.length(); i++)
            {
                JSONObject reviewItem = reviewResult.getJSONObject(i);
                String reviewerId = jsonObject.getString("id");
                String author = reviewItem.getString("author");
                String  content = reviewItem.getString("content");
                reviewList.add(new Review(movieID , reviewerId , author , content));
            }
            return reviewList;

        } catch (JSONException jsonException)
        {

        }
        return reviewList;
    }
}
