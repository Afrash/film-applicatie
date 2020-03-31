package com.example.filmapplicatie.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filmapplicatie.MainActivity;
import com.example.filmapplicatie.R;
import com.example.filmapplicatie.SeeListActivity;
import com.example.filmapplicatie.database.SQLiteDatabaseHandler;

public class ReviewDetailActivity extends AppCompatActivity {

    private static String TAG = ReviewDetailActivity.class.getName();

    private TextView title;
    private EditText review2;
    private EditText rating;
    private Button add;
    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new SQLiteDatabaseHandler(this);

        title = (TextView) findViewById(R.id.Movie_title_id);
        add = (Button) findViewById(R.id.review_detail_button_id);
        review2 = (EditText) findViewById(R.id.review_detail_editText_id);
        rating = (EditText) findViewById(R.id.rating) ;


        Intent intent = getIntent();
        String mTitle = intent.getExtras().getString("TITLE");
        title.setText(mTitle);

        //Review review = new Review(title.getText().toString(), review2.getText().toString(), rating.getText().toString());
        //db.addReviews(review);
       // Log.i(TAG, "content in review: " + review.toString());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review review = new Review(title.getText().toString(), review2.getText().toString(), rating.getText().toString());
                db.addReviews(review);
                Log.i(TAG, "This is in the database: " + db.allReviews());

                Intent i = new Intent(getApplicationContext(), SeeListActivity.class);
                i.putExtra("MOVIE", review.getMovie());
                i.putExtra("REVIEW", review.getReview());
                i.putExtra("RATING", review.getRating());



                Log.i(TAG, "go to next activity ");


            }
        });

    }


}
