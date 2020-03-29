package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SeeListActivity extends AppCompatActivity {

    private EditText movie;
    private EditText review;
    private EditText rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO recyclerview van maken om alles van database te weergeven, dus recyclerview met een detailactivityview zodat we alle data kunnen weergeven.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_list);

        movie = (EditText) findViewById(R.id.seeMovieTitle);
        review = (EditText) findViewById(R.id.showReview);
        rating = (EditText) findViewById(R.id.showRating) ;

        Intent i = getIntent();

        String movieText = i.getExtras().getString("MOVIE");
        String reviewText = i.getExtras().getString("REVIEW");
        String ratingText = i.getExtras().getString("RATING");

        movie.setText(movieText);
        review.setText(reviewText);
        rating.setText(ratingText);
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//    }
    //angelo?
}
