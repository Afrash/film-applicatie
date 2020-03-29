package com.example.filmapplicatie.movie;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmapplicatie.network.NetworkUtils;
import com.example.filmapplicatie.R;
import com.example.filmapplicatie.Sorter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Movie_Fragment extends Fragment implements View.OnClickListener, NetworkUtils.OnElementApiListener
        ,MovieOnClickHandler {

    private static String TAG = Movie_Fragment.class.getName();

    ArrayList<Movie> mMovies = new ArrayList<>();
    private EditText searchEditText;
    private Button searchButton;
    private TextView wholeText;
    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mAdapter;

    //Filter
    private boolean isChecked = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootview = inflater.inflate(R.layout.activity_movies, container, false);
        searchEditText = (EditText) rootview.findViewById(R.id.searchEditText);
        searchButton = (Button) rootview.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

        setHasOptionsMenu(true);

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=b6f53c81e5115a4f1b13c9f2e25785a0";

        NetworkUtils networkUtils = new NetworkUtils(this);

        Log.d(TAG, "initRecyclerView called");
        mRecyclerView = rootview.findViewById(R.id.recycler_view_element);
        //additemDecoration add een divider aan de recyclerview
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //// Create adapter passing in the elements data
        mAdapter = new MovieAdapter(mMovies, this);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(mAdapter);
        // Set layout manager to position the items
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);



        Log.d(TAG, "NetworkUtils is called");
        //execute is een methode van een de abstracte klasse Asynchtask
        networkUtils.execute(url);
return rootview;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.genre_menu, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "onOptionsItemSelected called");

        if(item.getItemId() == (R.id.genre_action)||
                item.getItemId() == (R.id.genre_animation)||
                item.getItemId() == (R.id.genre_comedy)||
                item.getItemId() == (R.id.genre_crime)||
                item.getItemId() == (R.id.genre_drama)||
                item.getItemId() == (R.id.genre_family)||
                item.getItemId() == (R.id.genre_fantasy)||
                item.getItemId() == (R.id.genre_historical)||
                item.getItemId() == (R.id.genre_horror)||
                item.getItemId() == (R.id.genre_mystery)||
                item.getItemId() == (R.id.genre_romance)||
                item.getItemId() == (R.id.genre_science_fiction)||
                item.getItemId() == (R.id.genre_thriller)||
                item.getItemId() == (R.id.genre_western)){
            Log.i(TAG, "onCreateOptionsMenu filter on action called");
                checkIfChecked(item);
                mAdapter.getFilter().filter(item.getTitle());
                return true;
        }

        switch(item.getItemId()){
            case R.id.menu_sort_alphabetical:
                Log.i(TAG, "onCreateOptionsMenu sort alphabetical called");
                item.setChecked(true);
                Collections.sort(mMovies, new Sorter.SortAlphabetical());
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_sort_most_popular:
                Log.i(TAG, "onCreateOptionsMenu sort most popular called");
                item.setChecked(true);
                Collections.sort(mMovies, new Sorter.SortMostPopular());
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_sort_least_popular:
                Log.i(TAG, "onCreateOptionsMenu sort least popular called");
                item.setChecked(true);
                Collections.sort(mMovies, new Sorter.SortMostPopular());
                mAdapter.notifyDataSetChanged();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

        public void checkIfChecked(MenuItem item){
            Log.i(TAG, "checkIfChecked called");
            isChecked = !item.isChecked();
            item.setChecked(isChecked);
    }

    @Override
    public void onClick(View v) {
        // dit pakt de query die ik zelf intik in de searchbar en geef het aan de buildURL(van de class NetworkUtils) zodat het de hele URL bouwt.
        String bolQuery = searchEditText.getText().toString();
        URL bolSearchURL = NetworkUtils.buildURL(bolQuery);
        Log.i(TAG, "This is in the SearchURL " + bolQuery);

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
        Toast.makeText(getContext(), "Amount of movies: " + this.mMovies.size()
                , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onElementClick(View view, int itemIndex) {
        //itemindex is op welke plaats het item staat in de recycleview.
        Log.d(TAG, "create intent");

        //je zegt over welke activity de intent gaat voor de startActivity
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);

        Log.d(TAG, "create variables to give to other class");

        //pakt data van element en kan het dan geven aan andere activiteit
        String title = this.mMovies.get(itemIndex).getTitle();
        String popularity = this.mMovies.get(itemIndex).getPopularity();
        String image = this.mMovies.get(itemIndex).getImage();
        String vote_count = this.mMovies.get(itemIndex).getVote_count();
        String vote_average = this.mMovies.get(itemIndex).getVote_average();
        String overview = this.mMovies.get(itemIndex).getOverview();
        String language = this.mMovies.get(itemIndex).getLanguage();
        String identificationNumber = this.mMovies.get(itemIndex).getIdentificationNumber();
        String release_date = this.mMovies.get(itemIndex).getRelease_date();
        String adult = this.mMovies.get(itemIndex).getAdult();
        //genre
        String genres = this.mMovies.get(itemIndex).getGenre();
//
//        //stop het in de intent zodat je de data kan krijgen in de andere class
        intent.putExtra("IMAGE", image);
        intent.putExtra("TITLE", title);
        intent.putExtra("POPULARITY", popularity);
        intent.putExtra("VOTE_COUNT", vote_count);
        intent.putExtra("VOTE_AVERAGE", vote_average);
        intent.putExtra("OVERVIEW", overview);
        intent.putExtra("LANGUAGE", language);
        intent.putExtra("IDENTIFICATIONNUMBER", identificationNumber);
        intent.putExtra("RELEASE_DATE", release_date);
        intent.putExtra("ADULT", adult);
        //genre
        intent.putExtra("GENRES", genres);


        Log.d(TAG, "start activity");
        startActivity(intent);

    }


}