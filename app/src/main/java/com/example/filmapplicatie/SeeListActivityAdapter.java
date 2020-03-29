package com.example.filmapplicatie;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.filmapplicatie.movie.MovieAdapter;
import com.example.filmapplicatie.review.Review;
import com.example.filmapplicatie.review.SeeOnClickHandler;

import java.util.List;

public class SeeListActivityAdapter extends RecyclerView.Adapter<SeeListActivityAdapter.ViewHolder>  {

    private final static String TAG = MovieAdapter.class.getName();
    private List<Review> mSeeList;
    private final SeeOnClickHandler SeeListClicker;

    public SeeListActivityAdapter(List<Review> mReviews, SeeOnClickHandler mSeeListClicker) {
        this.mSeeList = mReviews;
        this.SeeListClicker = mSeeListClicker;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-class: " + parent.getClass().getSimpleName());
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceName: " + parent.getContext().getResources().getResourceName(parent.getId()));
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceEntryName: " + parent.getContext().getResources().getResourceEntryName(parent.getId()));

        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);

        /* maakt een nieuwe view aan
           set xml view*/
        View reviewListItem = inflator.inflate(R.layout.seelist, parent, false);
        SeeListActivityAdapter.ViewHolder viewHolder2 = new ViewHolder(reviewListItem);

        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movie.setText(mSeeList.get(position).getMovie());
        holder.review.setText(mSeeList.get(position).getReview());
        holder.rating.setText(mSeeList.get(position).getRating());
    }



    @Override
    public int getItemCount() {
        return mSeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView movie;
        private TextView review;
        private TextView rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie = (TextView) itemView.findViewById(R.id.movie_title_a);
            review = (TextView) itemView.findViewById(R.id.review);
            rating = (TextView) itemView.findViewById(R.id.movie_rating);

            rating.setOnClickListener(this);
            movie.setOnClickListener(this);
            review.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.v(SeeListActivityAdapter.ViewHolder.class.getName(), "clicked on item");
            int itemIndex = getAdapterPosition();
            SeeListClicker.onReviewClick(v, itemIndex);
        }
    }

}
