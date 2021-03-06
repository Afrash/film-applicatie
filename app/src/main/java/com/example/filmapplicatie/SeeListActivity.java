package com.example.filmapplicatie;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import com.example.filmapplicatie.database.SQLiteDatabaseHandler;
import com.example.filmapplicatie.movie.Movie_Fragment;
import com.example.filmapplicatie.review.Review;
import com.example.filmapplicatie.review.SeeOnClickHandler;

import org.w3c.dom.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeeListActivity extends Fragment implements SeeOnClickHandler {

    private static String TAG = SeeListActivity.class.getName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button startScreen;
    private boolean isChecked = false;
    private ArrayList<Review> reviews;
    //make database
    SQLiteDatabaseHandler db;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_see_list, container, false);
        db = new SQLiteDatabaseHandler(getContext());



        Log.i(TAG, "This is in the database: " + db.allReviews());
        List<Review> reviews;
        Toolbar toolbar =  rootview.findViewById(R.id.toolbar);
        setHasOptionsMenu(true);


        //zet alle reviews van database in de arraylist.
        reviews = db.allReviews();


        //reviews.add(new Review(movieText, reviewText, ratingText));
        Log.i(TAG, "items in arrayList = " + reviews.size());

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.seelist_recycleview);
        //additemDecoration add een divider aan de recyclerview
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //// Create adapter passing in the elements data
        mAdapter = new SeeListActivityAdapter(reviews, this);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(mAdapter);
        // Set layout manager to position the items
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //check of arraylist changed.
        mAdapter.notifyDataSetChanged();

      return rootview;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //share button
        Log.i(TAG, "onOptionsItemSelected called");
        switch (item.getItemId()) {
            case R.id.share_button:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, " " +
                        db.allReviews());
//                intent.putExtra(Intent.EXTRA_TEXT, " " +
//                        db.allReviews());

                intent.putExtra(Intent.EXTRA_TITLE, "Send reviews");

                intent.setType("text/plain");
                getActivity().startActivity(Intent.createChooser(intent,"export"));

                // do stuff, like showing settings fragment
                break;
        } switch (item.getItemId()) {
            case R.id.delete_button:
                db.removeReviews();
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SeeListActivity()).commit();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onReviewClick(View view, int itemIndex) {
            //TODO moet ik later naar kijken als ik deze nog kan fixe, is nu niet belangrijk.
            //db.deleteOne(this.reviews.get(itemIndex).getMovie());
    }
}