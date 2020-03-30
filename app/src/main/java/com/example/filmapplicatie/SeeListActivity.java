package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filmapplicatie.movie.MovieAdapter;
import com.example.filmapplicatie.movie.Movie_Fragment;
import com.example.filmapplicatie.review.Review;
import com.example.filmapplicatie.review.SeeOnClickHandler;

import java.util.ArrayList;

public class SeeListActivity extends AppCompatActivity implements SeeOnClickHandler {

    private static String TAG = SeeListActivity.class.getName();
    ArrayList<Review> reviews;
    private EditText searchEditText;
    private Button searchButton;
    private TextView wholeText;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button startScreen;

    private EditText movie;
    private EditText review;
    private EditText rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO recyclerview van maken om alles van database te weergeven, dus recyclerview met een detailactivityview zodat we alle data kunnen weergeven.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_list);

        startScreen = (Button) findViewById(R.id.startscreen);


//        movie = (EditText) findViewById(R.id.seeMovieTitle);
//        review = (EditText) findViewById(R.id.showReview);
//        rating = (EditText) findViewById(R.id.showRating) ;
//
        Intent i = getIntent();

        String movieText = i.getExtras().getString("MOVIE");
        String reviewText = i.getExtras().getString("REVIEW");
        String ratingText = i.getExtras().getString("RATING");
        Log.i(TAG, "This is in the intent: " + movieText);
        Log.i(TAG, "This is in the intent: " + reviewText);
        Log.i(TAG, "This is in the intent: " + ratingText);
//
//        movie.setText(movieText);
//        review.setText(reviewText);
//        rating.setText(ratingText);
//
        ArrayList<Review> reviews = new ArrayList<>();


        reviews.add(new Review(movieText, reviewText, ratingText));
        Log.i(TAG, "items in arrayList = " + reviews.size());

        mRecyclerView = (RecyclerView) findViewById(R.id.seelist_recycleview);
        //additemDecoration add een divider aan de recyclerview
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //// Create adapter passing in the elements data
        mAdapter = new SeeListActivityAdapter(reviews, this);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(mAdapter);
        // Set layout manager to position the items
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //check of arraylist changed.
        mAdapter.notifyDataSetChanged();

        //go back to mainScreen
        startScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }




    @Override
    public void onReviewClick(View view, int itemIndex) {

    }
}