package com.example.filmapplicatie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ElementDetailActivity extends AppCompatActivity {

    private static String TAG = ElementDetailActivity.class.getName();

    private ImageView image;
    private TextView geographical_location;
    private TextView title;
    private TextView artist;
    private TextView description;
    private TextView material;
    private TextView underground;
    private TextView placement_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_detail);

        Log.i(TAG, "Activity is started");

        image = findViewById(R.id.image_detail);
        geographical_location = findViewById(R.id.geographical_location_detail);
        title = findViewById(R.id.title_detail);
        artist = findViewById(R.id.artist_detail);
        description = findViewById(R.id.description_detail);
        material = findViewById(R.id.material_detail);
        underground = findViewById(R.id.underground_detail);
        placement_date = findViewById(R.id.placement_date_detail);

        Log.i(TAG, "retrieve intent");
        Intent intent = getIntent();

        String mTitle = intent.getExtras().getString("TITLE");
        String mImage = intent.getExtras().getString("IMAGE");
        String mGeographical_location = intent.getExtras().getString("GEOGRAPHICAL_LOCATION");
        String mArtist = intent.getExtras().getString("ARTIST");
        String mDescription = intent.getExtras().getString("DESCRIPTION");
        String mMaterial = intent.getExtras().getString("MATERIAL");
        String mUnderground = intent.getExtras().getString("UNDERGROUND");
        String mPlacementDate = intent.getExtras().getString("PLACEMENT_DATE");

        //set the image
        Picasso.get()
                .load(mImage)
                .into(image);

        geographical_location.setText(mGeographical_location);
        title.setText(mTitle);
        artist.setText(mArtist);
        description.setText(mDescription);
        material.setText(mMaterial);
        underground.setText(mUnderground);
        placement_date.setText(mPlacementDate);

    }
}
