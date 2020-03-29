package com.example.filmapplicatie.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.filmapplicatie.R;
import com.example.filmapplicatie.review.ReviewDetailActivity;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private static String TAG = MovieDetailActivity.class.getName();

    private ImageView image;
    private TextView language;
    private TextView title;
    private TextView vote_Count;

    private TextView overview;
    private TextView release_date;
    private TextView adult;

    //RatingBar
    private RatingBar ratingBar;

    private TextView genre;
    private TextView popularity;
    private Button reviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_detail);

        Log.i(TAG, "Activity is started");

        image = findViewById(R.id.poster_imageview);
        language = findViewById(R.id.movie_language_in);
        title = findViewById(R.id.movie_title_in);
        ratingBar = findViewById(R.id.movie_rating_bar);
        adult = findViewById(R.id.movie_eighteenPlus_in);
        //TODO kijken wat we hiermee gaan doen met deze 2.
        overview = findViewById(R.id.movie_summary_in);
        release_date = findViewById(R.id.movie_releaseDate_in);
        genre = findViewById(R.id.movie_genre_in);

        Log.i(TAG, "retrieve intent");
        Intent intent = getIntent();



        String mTitle = intent.getExtras().getString("TITLE");
        String mImage = intent.getExtras().getString("IMAGE");
        String mLanguage = intent.getExtras().getString("LANGUAGE");
        String mVote_count = intent.getExtras().getString("VOTE_COUNT");
        String mVote_average = intent.getExtras().getString("VOTE_AVERAGE");
        String mOverview = intent.getExtras().getString("OVERVIEW");
        String mRelease_date = intent.getExtras().getString("RELEASE_DATE");
        String mPopularity = intent.getExtras().getString("POPULARITY");
        String mGenre = intent.getExtras().getString("GENRES");
        String mAdult = intent.getExtras().getString("ADULT");

        float f = Float.parseFloat(mVote_average);
        float amountOfStars = f/2;


        String midentificationNumber = intent.getExtras().getString("IDENTIFICATIONNUMBER");

        //set the image
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + mImage)
                .into(image);

//
       language.setText(mLanguage);
        title.setText(mTitle);
        genre.setText(mGenre);
        ratingBar.setNumStars(5);
        ratingBar.setRating(amountOfStars);
        overview.setText(mOverview);
        release_date.setText(mRelease_date);
        adult.setText(mAdult);
   //     identificationNumber.setText(midentificationNumber);
       // popularity.setText(mPopularity);


        reviewButton = (Button) findViewById(R.id.reviewButton);

        //go to review screen
        final Intent reviewIntent = new Intent(getApplicationContext(), ReviewDetailActivity.class);
        reviewIntent.putExtra("TITLE", mTitle);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(reviewIntent);
            }
        });

    }
}
