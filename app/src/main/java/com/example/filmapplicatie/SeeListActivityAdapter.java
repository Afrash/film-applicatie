package com.example.filmapplicatie;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapplicatie.movie.MovieAdapter;
import com.example.filmapplicatie.movie.MovieOnClickHandler;
import com.example.filmapplicatie.review.Review;

import java.util.List;

public class SeeListActivityAdapter extends RecyclerView.Adapter<SeeListActivity.ViewHolder>  {

    private final static String TAG = MovieAdapter.class.getName();
    private List<Review> mSeeList;


    public SeeListActivityAdapter(List<Review> mReviews, MovieOnClickHandler mSeeListClicker) {
        this.mSeeList = mReviews;
       // this.mSeeListClicker = mSeeListClicker;
    }


    @NonNull
    @Override
    public SeeListActivity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-class: " + parent.getClass().getSimpleName());
//        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceName: " + parent.getContext().getResources().getResourceName(parent.getId()));
//        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceEntryName: " + parent.getContext().getResources().getResourceEntryName(parent.getId()));
//
//        Context context = parent.getContext();
//        LayoutInflater inflator = LayoutInflater.from(context);
//
//        /* maakt een nieuwe view aan
//           set xml view*/
//        View elementListItem = inflator.inflate(R.layout.seelist, parent, false);
//       SeeListActivityAdapter.ViewHolder viewHolder2 = new SeeListActivityAdapter(elementListItem);
//
       return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SeeListActivity.ViewHolder holder, int position) {
//        holder.title.setText(mSeeList.get(position).getTitle());
//        holder.genre.setText(mSeeList.get(position).getGenre());
//        holder.rating.setText(mSeeList.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return mSeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView genre;
        private TextView rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            title = (TextView) itemView.findViewById(R.id);
//            genre= (TextView) itemView.findViewById(R.id.);
//            rating = (TextView) itemView.findViewById(R.id.);

            rating.setOnClickListener(this);
            title.setOnClickListener(this);
            genre.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.v(MovieAdapter.ViewHolder.class.getName(), "clicked on item");
            int itemIndex = getAdapterPosition();
            mMovieClicker.onElementClick(view, itemIndex);
        }
    }
}
