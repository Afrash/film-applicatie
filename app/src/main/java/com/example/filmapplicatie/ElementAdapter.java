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

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder> {

    /*RecyclerView.Adapter - om data af te handelen in de collectie en om het aan een view te verbinden zodat het data afhandeld
    LayoutManager - Helps van positioning van items*/
    private final static String TAG = ElementAdapter.class.getName();
    private List<Element> mElements;
    private final ElementOnClickHandler mElementClickHandler;

    public ElementAdapter(List<Element> mElements, ElementOnClickHandler mElementClickHandler){
        this.mElements = mElements;
        this.mElementClickHandler = mElementClickHandler;
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
        ElementAdapter.ViewHolder viewHolder = new ViewHolder(elementListItem);

        return viewHolder;
    }

    //set de view attributen gebaseerd op de data, vind de data met de viewholder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.v(TAG, "++++ onBindViewHolder-type: " + holder.getClass().getSimpleName());
        Element element = mElements.get(position);

        Picasso.get()
                .load(this.mElements.get(position).getImage())
                .into(holder.image);

        holder.title.setText(mElements.get(position).getTitle());
        holder.geographical_location.setText(mElements.get(position).getGeographicalLocation());
        holder.identification_nr.setText(mElements.get(position).getIdentificationNumber());
    }

    //bepaald het nummer van elementen in de lijst
    @Override
    public int getItemCount() {
        Log.v(TAG, "item count in Adapter = " + mElements.size());
        return mElements.size();
    }

    //met ViewHolder kan je bijhouden wat er gebeurd in je scherm
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView title;
        private TextView geographical_location;
        private TextView identification_nr;

        //geeft attributen aan XML
        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.element_item_imageview);
            title = (TextView) itemView.findViewById(R.id.element_title);
            geographical_location = (TextView) itemView.findViewById(R.id.geographic_location);
            identification_nr = (TextView) itemView.findViewById(R.id.identification_nr);
            //setOnClickListeners
            image.setOnClickListener(this);
            title.setOnClickListener(this);
            geographical_location.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        //dit wordt uitgevoerd als je ergens op klikt binnenin de view
        @Override
        public void onClick(View view) {
            Log.v(ViewHolder.class.getName(), "clicked on item");
            int itemIndex = getAdapterPosition();
            mElementClickHandler.onElementClick(view, itemIndex);
        }
    }
}