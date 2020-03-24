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
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkUtils.OnElementApiListener
        ,ElementOnClickHandler {

    private static String TAG = MainActivity.class.getName();

    ArrayList<Movie> mMovies = new ArrayList<>();
    private EditText searchEditText;
    private Button searchButton;
    private TextView wholeText;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String url = "https://api.themoviedb.org/3/discover/movie?api_key=b6f53c81e5115a4f1b13c9f2e25785a0";

        NetworkUtils networkUtils = new NetworkUtils(this);

        Log.d(TAG, "initRecyclerView called");
        mRecyclerView = findViewById(R.id.recycler_view_element);
        //additemDecoration add een divider aan de recyclerview
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //// Create adapter passing in the elements data
        mAdapter = new MovieAdapter(mMovies, this);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(mAdapter);
        // Set layout manager to position the items
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        Log.d(TAG, "NetworkUtils is called");
        //execute is een methode van een de abstracte klasse Asynchtask
        networkUtils.execute(url);
    }
@Override
    public void onClick(View v) {

        // dit pakt de query die ik zelf intik in de searchbar en geef het aan de buildURL(van de class NetworkUtils) zodat het de hele URL bouwt.
        String movieQuery = searchEditText.getText().toString();
        URL bolSearchURL = NetworkUtils.buildURL(movieQuery);

        Log.i(TAG, "onClick is called");
        NetworkUtils networkingTask = new NetworkUtils(this);

        //String url = bolSearchURL.toString();

        //execute is een methode van een de abstracte klasse Asynchtask
        networkingTask.execute(bolSearchURL.toString());
    }

    @Override
    public void onElementAvailable(ArrayList<Movie> elements) {
        Log.i(TAG, "elements = " + elements.toString());
        mMovies.clear();
        mMovies.addAll(elements);
        Log.i(TAG, "size of arrayList = " + mMovies.size());
        mAdapter.notifyDataSetChanged();
        //gives amount of elements
        Toast.makeText(getApplicationContext(), "Amount of movies: " + this.mMovies.size()
                , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onElementClick(View view, int itemIndex) {
        //itemindex is op welke plaats het item staat in de recycleview.
        Log.d(TAG, "create intent");

        //je zegt over welke activity de intent gaat voor de startActivity
        Intent intent = new Intent(getApplicationContext(), ElementDetailActivity.class);

        Log.d(TAG, "create variables to give to other class");

        //pakt data van element en kan het dan geven aan andere activiteit
//        String title = this.mElements.get(itemIndex).getTitle();
//        String geographical_location = this.mElements.get(itemIndex).getGeographicalLocation();
//        String image = this.mElements.get(itemIndex).getImage();
//        String artist = this.mElements.get(itemIndex).getArtist();
//        String description = this.mElements.get(itemIndex).getDescription();
//        String material = this.mElements.get(itemIndex).getMaterial();
//        String underground = this.mElements.get(itemIndex).getUnderground();
//        String placement_date = this.mElements.get(itemIndex).getPlacement_date();
//
//        //stop het in de intent zodat je de data kan krijgen in de andere class
//        intent.putExtra("TITLE", title);
//        intent.putExtra("GEOGRAPHICAL_LOCATION", geographical_location);
//        intent.putExtra("IMAGE", image);
//        intent.putExtra("ARTIST", artist);
//        intent.putExtra("DESCRIPTION", description);
//        intent.putExtra("MATERIAL", material);
//        intent.putExtra("UNDERGROUND", underground);
//        intent.putExtra("PLACEMENT_DATE", placement_date);

        Log.d(TAG, "start activity");
        startActivity(intent);

    }


}