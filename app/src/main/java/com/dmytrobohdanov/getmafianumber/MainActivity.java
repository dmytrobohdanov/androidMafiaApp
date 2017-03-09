package com.dmytrobohdanov.getmafianumber;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> numbers;

    TextView displayNumber;
    TextView freePlace1;
    TextView freePlace2;
    TextView freePlace3;
    TextView freePlace4;
    TextView freePlace5;
    TextView freePlace6;
    TextView freePlace7;
    TextView freePlace8;
    TextView freePlace9;
    TextView freePlace10;
    Button getNumber;
    Button startAgain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numbers = getNewFullArray();
        initViews();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        displayNumber.setVisibility(View.INVISIBLE);
    }

    private void initViews() {
        displayNumber = (TextView) findViewById(R.id.display_number);
        freePlace1 = (TextView) findViewById(R.id.free_places_1);
        freePlace2 = (TextView) findViewById(R.id.free_places_2);
        freePlace3 = (TextView) findViewById(R.id.free_places_3);
        freePlace4 = (TextView) findViewById(R.id.free_places_4);
        freePlace5 = (TextView) findViewById(R.id.free_places_5);
        freePlace6 = (TextView) findViewById(R.id.free_places_6);
        freePlace7 = (TextView) findViewById(R.id.free_places_7);
        freePlace8 = (TextView) findViewById(R.id.free_places_8);
        freePlace9 = (TextView) findViewById(R.id.free_places_9);
        freePlace10 = (TextView) findViewById(R.id.free_places_10);

        getNumber = (Button) findViewById(R.id.btn_get_number);
        getNumber.setOnClickListener(this);

        startAgain = (Button) findViewById(R.id.btn_start_again);
        startAgain.setOnClickListener(this);
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
        TextView unavailablePlace = null;

        switch (setUnavailableNumber) {
            case 1:
                unavailablePlace = (TextView) findViewById(R.id.free_places_1);
                break;

            case 2:
                unavailablePlace = (TextView) findViewById(R.id.free_places_2);
                break;

            case 3:
                unavailablePlace = (TextView) findViewById(R.id.free_places_3);
                break;

            case 4:
                unavailablePlace = (TextView) findViewById(R.id.free_places_4);
                break;

            case 5:
                unavailablePlace = (TextView) findViewById(R.id.free_places_5);
                break;

            case 6:
                unavailablePlace = (TextView) findViewById(R.id.free_places_6);
                break;

            case 7:
                unavailablePlace = (TextView) findViewById(R.id.free_places_7);
                break;

            case 8:
                unavailablePlace = (TextView) findViewById(R.id.free_places_8);
                break;

            case 9:
                unavailablePlace = (TextView) findViewById(R.id.free_places_9);
                break;

            case 10:
                unavailablePlace = (TextView) findViewById(R.id.free_places_10);
                break;
        }

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
}
