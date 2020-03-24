package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private static String TAG = MovieDetailActivity.class.getName();

    private ImageView image;
    private TextView language;
    private TextView title;
    private TextView vote_Count;
    private TextView vote_Average;
    private TextView overview;
    private TextView release_date;
    private TextView identificationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_detail);

        Log.i(TAG, "Activity is started");

          image = findViewById(R.id.poster_imageview);
//        geographical_location = findViewById(R.id.geographical_location_detail);
//        title = findViewById(R.id.title_detail);
//        artist = findViewById(R.id.artist_detail);
//        description = findViewById(R.id.description_detail);
//        material = findViewById(R.id.material_detail);
//        underground = findViewById(R.id.underground_detail);
//        placement_date = findViewById(R.id.placement_date_detail);
//
//        Log.i(TAG, "retrieve intent");
        Intent intent = getIntent();
//
//
//        String mTitle = intent.getExtras().getString("TITLE");
        String mImage = intent.getExtras().getString("IMAGE");
//        String mLanguage = intent.getExtras().getString("LANGUAGE");
//        String mVote_count = intent.getExtras().getString("VOTE_COUNT");
//        String mVote_average = intent.getExtras().getString("VOTE_AVERAGE");
//        String mOverview = intent.getExtras().getString("OVERVIEW");
//        String mRelease_date = intent.getExtras().getString("RELEASE_DATE");
//        String mPopularity = intent.getExtras().getString("POPULARITY");
//        String identificationNumber = intent.getExtras().getString("IDENTIFICATIONNUMBER");

        //set the image
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + mImage)
                .into(image);

//
//       language.setText(mLanguage);
//        title.setText(mTitle);
//        vote_Count.setText(mVote_count);
//        vote_Average.setText(mVote_average);
//        overview.setText(mOverview);
//        release_date.setText(mRelease_date);
//        identificationNumber.setText(mIdentificationNumber);

    }
}
