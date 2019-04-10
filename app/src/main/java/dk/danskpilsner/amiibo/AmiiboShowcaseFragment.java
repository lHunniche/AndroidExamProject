package dk.danskpilsner.amiibo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dk.danskpilsner.amiibo.models.Amiibo;

public class AmiiboShowcaseFragment extends Fragment {
    final static String POSITION = "position";
    int mCurrentPosition = -1;
    private ImageView amiiboShowcase;
    private TextView amiiboName, amiiboGameSeries, amiiboSeries, AUreleaseDate, EUreleaseDate, JPreleaseDate, NAreleaseDate;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        amiiboShowcase = view.findViewById(R.id.imgview_amiiboShowcase);
        amiiboName = view.findViewById(R.id.txtview_amiiboShowcase_characterName);
        amiiboGameSeries = view.findViewById(R.id.txtview_amiiboShowcase_gameSeries);
        amiiboSeries = view.findViewById(R.id.txtview_amiiboShowcase_amiiboSeries);
        AUreleaseDate = view.findViewById(R.id.txtview_amiiboShowcase_AUreleaseDate);
        EUreleaseDate = view.findViewById(R.id.txtview_amiiboShowcase_EUreleaseDate);
        JPreleaseDate = view.findViewById(R.id.txtview_amiiboShowcase_JPreleaseDate);
        NAreleaseDate = view.findViewById(R.id.txtview_amiiboShowcase_NAreleaseDate);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(POSITION);
        }

        // Inflate the layout for this fragment
        amiiboShowcase = (ImageView) inflater.inflate(R.layout.fragment_amiiboshowcase, container, false);
        return amiiboShowcase;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current amiibo selection (in case we need to recreate the fragment)
        outState.putInt(POSITION, mCurrentPosition);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            
            updatePosition(args.getInt(POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updatePosition(mCurrentPosition);
        }
    }
    public void updatePosition(int position){
        mCurrentPosition = position;
    }

    public void setDetails(Amiibo amiibo) {
        amiiboName.setText(amiibo.getName());
        amiiboGameSeries.setText(amiibo.getGameSeries());
        amiiboSeries.setText(amiibo.getAmiiboSeries());
        AUreleaseDate.setText(amiibo.getRelease().getAu());
        EUreleaseDate.setText(amiibo.getRelease().getEu());
        JPreleaseDate.setText(amiibo.getRelease().getJp());
        NAreleaseDate.setText(amiibo.getRelease().getNa());

    }

}
