/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidbasicsnanodegree.sbl.pornictourguideapp.LocationFragment.OnListFragmentInteractionListener;

import java.util.List;

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> {

    private List<Location> values;
    private final OnListFragmentInteractionListener listener;

    // Setting a custom filter
    public void setFilter(List<Location> filtered) {
        values = filtered ;
    }

    //The constructor shall retrieve the appropriate list of locations

    public LocationRecyclerViewAdapter(List<Location> items, OnListFragmentInteractionListener listener) {
        values = items;
        this.listener = listener;
    }

    //Following method shall inflate the appropriate layout file
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_location, parent, false);
        return new ViewHolder(view);
    }

    //Following method is setting the informations for each location in the list
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = values.get(position);
        holder.title.setText(values.get(position).getName());
        holder.subTitle.setText(values.get(position).getOpeningDays());
        holder.image.setImageResource(values.get(position).getImageId());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onListFragmentInteraction(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    //The ViewHolder is defined in order to know which views are going to receive information from
    //the Location object
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView title;
        private TextView subTitle;
        private ImageView image;
        private Location item;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            title = view.findViewById(R.id.locationTitle);
            subTitle = view.findViewById(R.id.locationSubtitle);
            image = view.findViewById(R.id.locationImageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + subTitle.getText() + "'";
        }
    }
}
