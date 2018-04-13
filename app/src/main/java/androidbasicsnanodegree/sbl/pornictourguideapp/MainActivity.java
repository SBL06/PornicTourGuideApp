/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnListFragmentInteractionListener {

    public static List<Location> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = buildAllLocationsList();

        ViewPager pager = findViewById(R.id.listPager);
        FragmentPagerAdapter adapter = new CategoryAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.mainActivityTabTitles));
        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }

    // The following method is used to retrieve all the datas about locations and implements them 
    // in an ArrayList 
    private ArrayList<Location> buildAllLocationsList() {

        ArrayList<Location> dataList = new ArrayList<>();

        //Harbour
        addLocation(dataList, R.string.harbourName, R.string.harbourOpeningDays,
                R.integer.harbourCategory, R.drawable.harbourImage, R.string.harbourDescription,
                R.string.harbourInformations, R.raw.harbour);

        //Castle
        addLocation(dataList, R.string.castleName, R.string.castleOpeningDays,
                R.integer.castleCategory, R.drawable.castleImage, R.string.castleDescription,
                R.string.castleInformations, R.raw.castle);

        return dataList;
    }

    // Following method is used to populate the ArrayList
    private void addLocation(ArrayList<Location> dataList, int nameId, int openingId, int category,
                             int imageId, int descriptionID, int informationId, int audioId) {
        Location toAdd = new Location(getString(nameId), getString(openingId),
                getResources().getInteger(category), imageId, getString(descriptionID),
                getString(informationId), audioId);
        dataList.add(toAdd);

    }

    // The following method is called whenever a location is clicked in the list
    @Override
    public void onListFragmentInteraction(Location location) {

        Location current = location;
        Intent intent = new Intent(this, LocationActivity.class);
        intent.putExtra("passed_location", current);
        startActivity(intent);
    }
}
