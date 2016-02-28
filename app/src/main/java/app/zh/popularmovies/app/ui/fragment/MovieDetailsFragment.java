package app.zh.popularmovies.app.ui.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.zh.popularmovies.app.R;
import butterknife.Bind;
import butterknife.ButterKnife;

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
    public static final String DETAIL_URI = "URI";
    private Uri mUri;

    public MovieDetailsFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        Bundle arguments = getArguments();
        if (arguments != null)
        {
            mUri = arguments.getParcelable(DETAIL_URI);
        }
        View rootView = inflater.inflate(R.layout.movie_details_activity, container, false);
        ButterKnife.bind(getActivity());
        return rootView;
    }
}
