package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ReviewDetailActivity extends AppCompatActivity {

    private static String TAG = ReviewDetailActivity.class.getName();

    private TextView title;
    private ImageView image;
    private TextView reviewId;
    private TextView ratingAverage;
    private TextView numberOfVotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        title = (TextView) findViewById(R.id.Movie_title_id);


        Intent intent = getIntent();




        String mTitle = intent.getExtras().getString("TITLE");
//        String mImage = intent.getExtras().getString("IMAGE");
//
//
//        //set the image
//        Picasso.get()
//                .load("https://image.tmdb.org/t/p/w500" + mImage)
//                .into(image);
//
//
        title.setText(mTitle);


    }
}
