package app.zh.popularmovies.app.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.zh.popularmovies.app.DependencyFactory;
import app.zh.popularmovies.app.FetchComplete;
import app.zh.popularmovies.app.R;
import app.zh.popularmovies.app.convertor.MovieConvertor;
import app.zh.popularmovies.app.features.IFavoriteFeature;
import app.zh.popularmovies.app.models.Movie;
import app.zh.popularmovies.app.models.Review;
import app.zh.popularmovies.app.models.Trailer;
import app.zh.popularmovies.app.network.FetchReviewTask;
import app.zh.popularmovies.app.network.FetchVideoTask;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsFragment extends Fragment
{
    private TextView titleView;
    private TextView releaseDateView;
    private TextView overView;
    private ImageView posterView;
    private TextView trailerText;
    private TextView _addFavorite;
    private LinearLayout _trailerContainer;
    private TextView firstTrailer;
    private TextView secondTrailer;
    private TextView reviewDescription;
    private TextView firstReview;
    private TextView secondReview;
    private TextView ratingView;
    private MenuItem shareItem;

    public static final String DETAIL_URI = "URI";
    private Movie movie;
    private ArrayList<Trailer> _trailerArrayList;
    private ArrayList<Review> _reviewArrayList;
    private IFavoriteFeature _favoriteFeature;

    public MovieDetailsFragment()
    {

    }

    @Override
    public void onStart()
    {
        super.onStart();
        fetchReviews();
        fetchTrailers();
    }

    private void initView(View view)
    {
        titleView = ((TextView) view.findViewById(R.id.title));
        _addFavorite = ((TextView) view.findViewById(R.id.add_favorite));
        ratingView = ((TextView) view.findViewById(R.id.vote_avg));
        posterView = ((ImageView) view.findViewById(R.id.poster));
        overView = ((TextView) view.findViewById(R.id.overview));
        releaseDateView = ((TextView) view.findViewById(R.id.release_date));
        trailerText = ((TextView) view.findViewById(R.id.trailer_text));
        firstTrailer = ((TextView) view.findViewById(R.id.trailer_1));
        secondTrailer = ((TextView) view.findViewById(R.id.trailer_2));
        reviewDescription = ((TextView) view.findViewById(R.id.review_text));
        firstReview = ((TextView) view.findViewById(R.id.review1));
        secondReview = ((TextView) view.findViewById(R.id.review2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle arguments = getArguments();
        if (arguments != null)
        {
            try
            {
                Log.d("movie details fragment", arguments.getString(DETAIL_URI));
                movie = new MovieConvertor().getMovieFromJsonString(arguments.getString(DETAIL_URI));
            } catch (JSONException jsonEception)
            {

            }
        }
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.movie_detail_fragment, container, false);
        ButterKnife.bind(getActivity());
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        _favoriteFeature = DependencyFactory.getFavoriteFeature();
        initView(view);
        renderVIew();
        super.onViewCreated(view, savedInstanceState);
    }

    private void renderVIew()
    {
        titleView.setText(movie.getTitle());
        Picasso.with(getActivity()).load("https://image.tmdb.org/t/p/w185" + movie.getPosterPath()).into(posterView);
        releaseDateView.setText(movie.getReleaseDate());
        ratingView.setText("" + movie.get_voteAverage());
        overView.setText(movie.getOverView());
        if (!_favoriteFeature.isFavorite(movie.getMovieId()))
        {
            _addFavorite.setText("Add Favorite");
        } else
        {
            _addFavorite.setText("Remove Favorite");
        }
        _addFavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (_favoriteFeature.isFavorite(movie.getMovieId()))
                {
                    _favoriteFeature.removeFavorite(movie.getMovieId());
                    _addFavorite.setText("Add Favorite");
                } else
                {
                    _favoriteFeature.addToFavorite(movie);
                    _addFavorite.setText("Remove Favorite");
                }
            }
        });
    }


    private void fetchTrailers()
    {

        FetchVideoTask fetchVideoTask = new FetchVideoTask(movie, getTrailerInterFace());
        fetchVideoTask.execute();
    }

    private void fetchReviews()
    {
        FetchReviewTask fetchReviewTask = new FetchReviewTask(movie, getReviewInterface());
        fetchReviewTask.execute();
    }

    private FetchComplete<Review> getReviewInterface()
    {
        return new FetchComplete<Review>()
        {
            @Override
            public void onFetchComplete(ArrayList<Review> objectList)
            {
                int size = objectList.size();
                _reviewArrayList = objectList;
                if (size == 1)
                {
                    shareItem.setVisible(true);
                    reviewDescription.setVisibility(View.VISIBLE);
                    firstReview.setVisibility(View.VISIBLE);
                    firstReview.setText(objectList.get(0).getAuthor() + ":   " + objectList.get(0).getContent());
                } else if (size != 0)
                {
                    shareItem.setVisible(true);
                    reviewDescription.setVisibility(View.VISIBLE);
                    firstReview.setVisibility(View.VISIBLE);
                    secondReview.setVisibility(View.VISIBLE);
                    firstReview.setText(objectList.get(0).getAuthor() + ":   " + objectList.get(0).getContent());
                    secondReview.setText(objectList.get(1).getAuthor() + ":   " + objectList.get(1).getContent());
                }
            }
        };
    }

    private FetchComplete<Trailer> getTrailerInterFace()
    {
        return new FetchComplete<Trailer>()
        {
            @Override
            public void onFetchComplete(ArrayList<Trailer> objectList)
            {
                int size = objectList.size();
                _trailerArrayList = objectList;
                if (size == 1)
                {
                    Log.d("trailer reault", "trialer result");
                    trailerText.setVisibility(View.VISIBLE);
                    firstTrailer.setVisibility(View.VISIBLE);
                    firstTrailer.setText(_trailerArrayList.get(0).getName());
                    firstTrailer.setOnClickListener(getTrailerClickListener(_trailerArrayList.get(0).getKey()));
                } else if (size != 0)
                {
                    reviewDescription.setVisibility(View.VISIBLE);
                    firstReview.setVisibility(View.VISIBLE);
                    secondReview.setVisibility(View.VISIBLE);
                    firstTrailer.setText(_trailerArrayList.get(0).getName());
                    secondTrailer.setText(_trailerArrayList.get(1).getName());
                    firstTrailer.setOnClickListener(getTrailerClickListener(_trailerArrayList.get(0).getKey()));
                    secondTrailer.setOnClickListener(getTrailerClickListener(_trailerArrayList.get(1).getKey()));
                }
            }
        };
    }

    private View.OnClickListener getTrailerClickListener(final String key)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key)));
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_details, menu);
        MenuItem menuItem = menu.findItem(R.id.share_item);
        if (_trailerArrayList != null && _trailerArrayList.size() != 0)
        {
            menuItem.setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.share_item:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_SUBJECT, movie.getTitle() + " Trailer");
                intent.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com/watch?v=" + _trailerArrayList.get(0).getKey());
                intent.setType("text/plain");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
