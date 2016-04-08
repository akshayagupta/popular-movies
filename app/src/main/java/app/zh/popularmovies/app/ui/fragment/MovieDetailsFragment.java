package app.zh.popularmovies.app.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.models.Movie;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieDetailsFragment extends Fragment
{

    @Bind(R.id.title)
    TextView titleView;
    @Bind(R.id.release_date)
    TextView releaseDateView;
    @Bind(R.id.vote_avg)
    TextView voteAvgView;
    @Bind(R.id.overview)
    TextView overView;
    @Bind(R.id.poster)
    ImageView posterView;
    @Bind(R.id.trailer_text)
    TextView trailerText;
    @Bind(R.id.add_favorite)
    TextView _addFavorite;
    @Bind(R.id.trailer_container)
    LinearLayout _trailerContainer;
    public static final String DETAIL_URI = "URI";
    private Movie movie;

    public MovieDetailsFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        Bundle arguments = getArguments();
        if (arguments != null)
        {
            try
            {

                movie = new MovieConvertor().getMovieFromJsonString(arguments.getString(DETAIL_URI));
            } catch (JSONException jsonEception)
            {

            }
        }
        View rootView = inflater.inflate(R.layout.movie_detail_fragment, null);
        ButterKnife.bind(getActivity());
        renderVIew();
        return rootView;
    }

    private void renderVIew()
    {
        titleView.setText(movie.getTitle());
        Picasso.with(getActivity()).load("https://image.tmdb.org/t/p/w185" + movie.getPosterPath()).into(posterView);
        releaseDateView.setText(movie.getReleaseDate());
        voteAvgView.setText("" + movie.get_voteAverage());
        overView.setText(movie.getReleaseDate());
        final List<String> keyList = movie.getVideoKeyList();
        if (keyList.size() != 0)
        {
            trailerText.setVisibility(View.VISIBLE);
            _trailerContainer.setVisibility(View.VISIBLE);
            for (int i = 0; i < keyList.size(); i++)
            {
                final int currentPos = i;
                TextView textView = new TextView(getActivity());
                textView.setText("Trailer " + (currentPos + 1));
                textView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        openVideo(currentPos);
                    }
                });
                _trailerContainer.addView(textView);
            }
        }
    }

    private void openVideo(int currentPos)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + movie.getVideoKeyList().get(currentPos))));
    }


}
