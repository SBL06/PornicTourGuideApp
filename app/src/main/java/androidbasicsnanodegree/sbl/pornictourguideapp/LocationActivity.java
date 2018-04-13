/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    //This activity is going to display informations about a specific chosen location
    //which is declared here

    Location CurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // The following lines are going to retrieve the chosen location

        Bundle passedLocation = getIntent().getExtras();
        CurrentLocation = (Location) passedLocation.getParcelable("passed_location");

        //View of the top frame are initialized here

        TextView name = findViewById(R.id.locationActivityTitle);
        TextView openingDays = findViewById(R.id.locationActivitySubtitle);
        ImageView image = findViewById(R.id.locationActivityImage);

        // Informations are provided to the views

        name.setText(CurrentLocation.getName());
        openingDays.setText(CurrentLocation.getOpeningDays());
        image.setImageResource(CurrentLocation.getImageId());

        // The description and the informations about a location are going to be displayed by Fragments
        // in a viewpager. Following lines are setting up the pager

        ViewPager pager = findViewById(R.id.locationPager);

        FragmentPagerAdapter adapter = new ContentAdapter(getSupportFragmentManager(),
                CurrentLocation.getDescription(), CurrentLocation.getInformations(),
                CurrentLocation.getAudioId(), getResources().getStringArray(R.array.locationActivityTabTitles));

        pager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.locationTabLayout);
        tabLayout.setupWithViewPager(pager);
    }
}
