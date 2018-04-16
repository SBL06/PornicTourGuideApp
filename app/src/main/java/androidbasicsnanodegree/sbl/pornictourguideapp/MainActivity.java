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

        //faiencerie
        addLocation(dataList, R.string.faiencerieName, R.string.faiencerieOpeningDays,
                R.integer.faiencerieCategory, R.drawable.faiencerieImage, R.string.faiencerieDescription,
                R.string.faiencerieInformations, R.raw.faiencerie);

        //piverre
        addLocation(dataList, R.string.piverreName, R.string.piverreOpeningDays,
                R.integer.piverreCategory, R.drawable.piverreImage, R.string.piverreDescription,
                R.string.piverreInformations, R.raw.piverre);

        //aquacenter
        addLocation(dataList, R.string.aquacenterName, R.string.aquacenterOpeningDays,
                R.integer.aquacenterCategory, R.drawable.aquacenterImage, R.string.aquacenterDescription,
                R.string.aquacenterInformations, R.raw.aquacenter);

        //aventure
        addLocation(dataList, R.string.aventureName, R.string.aventureOpeningDays,
                R.integer.aventureCategory, R.drawable.aventureImage, R.string.aventureDescription,
                R.string.aventureInformations, R.raw.aventure);

        //valstmartin
        addLocation(dataList, R.string.valstmartinName, R.string.valstmartinOpeningDays,
                R.integer.valstmartinCategory, R.drawable.valstmartinImage, R.string.valstmartinDescription,
                R.string.valstmartinInformations, R.raw.valstmartin);

        //villanoe
        addLocation(dataList, R.string.villanoeName, R.string.villanoeOpeningDays,
                R.integer.villanoeCategory, R.drawable.villanoeImage, R.string.villanoeDescription,
                R.string.villanoeInformations, R.raw.villanoe);

        //casinorestaurant
        addLocation(dataList, R.string.casinorestaurantName, R.string.casinorestaurantOpeningDays,
                R.integer.casinorestaurantCategory, R.drawable.casinorestaurantImage, R.string.casinorestaurantDescription,
                R.string.casinorestaurantInformations, R.raw.casinorestaurant);

        //casahernesto
        addLocation(dataList, R.string.casahernestoName, R.string.casahernestoOpeningDays,
                R.integer.casahernestoCategory, R.drawable.casahernestoImage, R.string.casahernestoDescription,
                R.string.casahernestoInformations, R.raw.casahernesto);

        //crescendo
        addLocation(dataList, R.string.crescendoName, R.string.crescendoOpeningDays,
                R.integer.crescendoCategory, R.drawable.crescendoImage, R.string.crescendoDescription,
                R.string.crescendoInformations, R.raw.crescendo);

        //beausoleil
        addLocation(dataList, R.string.beausoleilName, R.string.beausoleilOpeningDays,
                R.integer.beausoleilCategory, R.drawable.beausoleilImage, R.string.beausoleilDescription,
                R.string.beausoleilInformations, R.raw.beausoleil);

        //aliance
        addLocation(dataList, R.string.alianceName, R.string.alianceOpeningDays,
                R.integer.alianceCategory, R.drawable.alianceImage, R.string.alianceDescription,
                R.string.alianceInformations, R.raw.aliance);

        //voletsbleus
        addLocation(dataList, R.string.voletsbleusName, R.string.voletsbleusOpeningDays,
                R.integer.voletsbleusCategory, R.drawable.voletsbleusImage, R.string.voletsbleusDescription,
                R.string.voletsbleusInformations, R.raw.voletsbleus);

        //lilirose
        addLocation(dataList, R.string.liliroseName, R.string.liliroseOpeningDays,
                R.integer.liliroseCategory, R.drawable.liliroseImage, R.string.liliroseDescription,
                R.string.liliroseInformations, R.raw.lilirose);

        //belleilloise
        addLocation(dataList, R.string.belleilloiseName, R.string.belleilloiseOpeningDays,
                R.integer.belleilloiseCategory, R.drawable.belleilloiseImage, R.string.belleilloiseDescription,
                R.string.belleilloiseInformations, R.raw.belleilloise);

        //comptoir
        addLocation(dataList, R.string.comptoirName, R.string.comptoirOpeningDays,
                R.integer.comptoirCategory, R.drawable.comptoirImage, R.string.comptoirDescription,
                R.string.comptoirInformations, R.raw.comptoir);

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
