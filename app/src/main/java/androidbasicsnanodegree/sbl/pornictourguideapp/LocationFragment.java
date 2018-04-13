/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int columnCount = 1;
    private OnListFragmentInteractionListener listener;
    private int category;
    public LocationRecyclerViewAdapter adapter ;
    public RecyclerView recyclerView ;

    public LocationFragment() {
        //Required empty constructor
    }

    //The category parameter is passed in the newInstance() method by the CategoryAdapter
    public static LocationFragment newInstance(int columnCount, int category) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Arguments passed by the CategoryAdapter are retrieved here
        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            category = getArguments().getInt("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_list, container, false);

        // All the locations of the app are first listed in a common list, which is filtered
        // to show only the locations corresponding to the chosen category

        final List<Location> rawList = MainActivity.dataList;
        final List<Location> finalList = new ArrayList<>();

        for (int i = 0; i < rawList.size(); i++) {
            Location current = rawList.get(i);
            int category = current.getCategory();
            if (category == this.category) {
                finalList.add(current);
            }
        }

        // Setting the customized filter
        EditText filterField = getActivity().findViewById(R.id.filter);

        filterField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().toLowerCase();
                final List<Location> filteredModelList = new ArrayList<>();
                for (Location model : finalList) {
                    final String text = model.getName().toLowerCase();
                    if (text.contains(query)) {
                        filteredModelList.add(model);
                    }
                }
                adapter.setFilter(filteredModelList);
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new LocationRecyclerViewAdapter(finalList, listener) ;
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    //Following method is used to pass parameters to the main activity when an item is clicked. That
    //way it will be possible to launch the location activity
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Location location);
    }
}
