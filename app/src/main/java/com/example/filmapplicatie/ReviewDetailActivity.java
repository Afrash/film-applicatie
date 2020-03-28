package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        title = (TextView) findViewById(R.id.Movie_title_id);
        add = (Button) findViewById(R.id.review_detail_button_id);
        review2 = (EditText) findViewById(R.id.review_detail_editText_id);
        rating = (EditText) findViewById(R.id.rating) ;


        Intent intent = getIntent();
        String mTitle = intent.getExtras().getString("TITLE");
        title.setText(mTitle);

        final Review review = new Review(title.getText().toString(), review2.getText().toString(), rating.getText().toString());
        Log.i(TAG, "content in review: " + review.toString());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db.addReviews(review);
            }
        });

    }
}
