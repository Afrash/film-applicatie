package com.example.filmapplicatie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    /*RecyclerView.Adapter - om data af te handelen in de collectie en om het aan een view te verbinden zodat het data afhandeld
    LayoutManager - Helps van positioning van items*/
    private final static String TAG =MovieAdapter.class.getName();
    private List<Movie> mMovies;
    private final MovieOnClickHandler mMovieClicker;

    public MovieAdapter(List<Movie> mMovies, MovieOnClickHandler mMovieClicker) {
        this.mMovies = mMovies;
        this.mMovieClicker = mMovieClicker;
    }

    //inflate de item layout en maakt de Holder.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-class: " + viewGroup.getClass().getSimpleName());
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceName: " + viewGroup.getContext().getResources().getResourceName(viewGroup.getId()));
        Log.v(TAG, "++++++++ onCreateViewHolder - viewGroup-resourceEntryName: " + viewGroup.getContext().getResources().getResourceEntryName(viewGroup.getId()));

        Context context = viewGroup.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);

        /* maakt een nieuwe view aan
           set xml view*/
        View elementListItem = inflator.inflate(R.layout.element_item, viewGroup, false);
        MovieAdapter.ViewHolder viewHolder = new ViewHolder(elementListItem);

        return viewHolder;
    }

    //set de view attributen gebaseerd op de data, vind de data met de viewholder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.v(TAG, "++++ onBindViewHolder-type: " + holder.getClass().getSimpleName());
        Movie movie = mMovies.get(position);


        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + this.mMovies.get(position).getImage())
                .into(holder.image);
        Log.v(TAG, "Data in image: " + this.mMovies.get(position).getImage());



        holder.title.setText(mMovies.get(position).getTitle());
        holder.overview.setText(mMovies.get(position).getOverview());
        holder.identification_nr.setText(mMovies.get(position).getIdentificationNumber());
    }

    //bepaald het nummer van elementen in de lijst
    @Override
    public int getItemCount() {
        Log.v(TAG, "item count in Adapter = " + mMovies.size());
        return mMovies.size();
    }

    //met ViewHolder kan je bijhouden wat er gebeurd in je scherm
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView title;
        private TextView overview;
        private TextView identification_nr;

        //geeft attributen aan XML
        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.element_item_imageview);
            title = (TextView) itemView.findViewById(R.id.element_title);
            overview = (TextView) itemView.findViewById(R.id.overview);
            identification_nr = (TextView) itemView.findViewById(R.id.identification_nr);
            //setOnClickListeners
            image.setOnClickListener(this);
            title.setOnClickListener(this);
            overview.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        //dit wordt uitgevoerd als je ergens op klikt binnenin de view
        @Override
        public void onClick(View view) {
            Log.v(ViewHolder.class.getName(), "clicked on item");
            int itemIndex = getAdapterPosition();
            mMovieClicker.onElementClick(view, itemIndex);
        }
    }
}