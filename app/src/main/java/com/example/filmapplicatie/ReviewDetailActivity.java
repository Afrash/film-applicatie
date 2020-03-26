package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReviewDetailActivity extends AppCompatActivity {

    private static String TAG = ReviewDetailActivity.class.getName();

    private TextView reviewId;
    private TextView ratingAverage;
    private TextView numberOfVotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);




    }
}
