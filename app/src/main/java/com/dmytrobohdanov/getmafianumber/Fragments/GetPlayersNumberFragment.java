package com.dmytrobohdanov.getmafianumber.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dmytrobohdanov.getmafianumber.R;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GetPlayersNumberFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "getPlayersNumberFrTag";
    @BindView(R.id.display_number)
    TextView displayNumber;
    @BindView(R.id.free_places_1)
    TextView freePlace1;
    @BindView(R.id.free_places_2)
    TextView freePlace2;
    @BindView(R.id.free_places_3)
    TextView freePlace3;
    @BindView(R.id.free_places_4)
    TextView freePlace4;
    @BindView(R.id.free_places_5)
    TextView freePlace5;
    @BindView(R.id.free_places_6)
    TextView freePlace6;
    @BindView(R.id.free_places_7)
    TextView freePlace7;
    @BindView(R.id.free_places_8)
    TextView freePlace8;
    @BindView(R.id.free_places_9)
    TextView freePlace9;
    @BindView(R.id.free_places_10)
    TextView freePlace10;
    @BindView(R.id.btn_get_number)
    Button getNumber;
    @BindView(R.id.btn_start_again)
    Button startAgain;
    private ArrayList<Integer> numbers;


    public GetPlayersNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    private void initViews(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_get_players_number, container, false);

        ButterKnife.bind(this, rootView);
        getNumber.setOnClickListener(this);
        startAgain.setOnClickListener(this);

        numbers = getNewFullArray();
        initViews(rootView);

        displayNumber.setVisibility(View.INVISIBLE);

        return rootView;
    }

    private int getNewPositionInArray() {
        return (numbers.size() == 0) ? 0 : Math.abs((new Random()).nextInt()) % numbers.size();
    }


    /**
     * Initializing array of free places
     */
    private ArrayList<Integer> getNewFullArray() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        return numbers;
    }


    /**
     * Set view with seat number unavailable
     *
     * @param setUnavailableNumber number of seat to set unavailable
     */
    protected void setUnavailable(int setUnavailableNumber) {
        if (!(setUnavailableNumber >= 1 && setUnavailableNumber <= 10)) {
            throw new IllegalArgumentException("setUnavailableNumber could be only between 1 and 10");
        }

        TextView unavailablePlace = null;
        switch (setUnavailableNumber) {
            case 1:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_1);
                unavailablePlace = freePlace1;
                break;

            case 2:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_2);
                unavailablePlace = freePlace2;
                break;

            case 3:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_3);
                unavailablePlace = freePlace3;
                break;

            case 4:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_4);
                unavailablePlace = freePlace4;
                break;

            case 5:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_5);
                unavailablePlace = freePlace5;
                break;

            case 6:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_6);
                unavailablePlace = freePlace6;
                break;

            case 7:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_7);
                unavailablePlace = freePlace7;
                break;

            case 8:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_8);
                unavailablePlace = freePlace8;
                break;

            case 9:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_9);
                unavailablePlace = freePlace9;
                break;

            case 10:
//                unavailablePlace = (TextView) findViewById(R.id.free_places_10);
                unavailablePlace = freePlace10;
                break;
        }

        assert unavailablePlace != null;
        unavailablePlace.setAlpha(0.3f);
    }


    /**
     * Set seats specified seats available
     *
     * @param views to set available
     */
    protected void setAvailable(View... views) {
        for (View v : views)
            v.setAlpha(1);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_number:
                //set enable false if it was last position in array
                if (numbers.size() == 1) {
                    getNumber.setEnabled(false);
                }

                //get random position in array
                int positionInArray = getNewPositionInArray();

                //get players number
                int playersNumber = numbers.get(positionInArray);

                //remove players number from array of available places
                numbers.remove(positionInArray);

                //displaying players place
                displayNumber.setText(String.valueOf(playersNumber));
                displayNumber.setVisibility(View.VISIBLE);

                //set unavailable place (displaying)
                setUnavailable(playersNumber);
                break;

            case R.id.btn_start_again:
                displayNumber.setVisibility(View.INVISIBLE);
                getNumber.setEnabled(true);
                numbers = getNewFullArray();

                setAvailable(freePlace1, freePlace2, freePlace3, freePlace4, freePlace5,
                        freePlace6, freePlace7, freePlace8, freePlace9, freePlace9, freePlace10);
                break;
        }
    }
}
