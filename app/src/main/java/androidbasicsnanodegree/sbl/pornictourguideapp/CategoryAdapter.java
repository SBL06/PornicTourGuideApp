/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;

    //The Constructor accepts a string array parameter in order to set the tabs titles.
    // The parameter is sent from Main_Activity, because it is able to retrieve resources easily

    public CategoryAdapter(FragmentManager fm, String[] tabArray) {
        super(fm);
        tabTitles = tabArray;
    }

    // Following method is overridden in order to set the content of each tab. Each position number
    // corresponds to a specific category

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return LocationFragment.newInstance(1, 0);
        } else if (position == 1) {
            return LocationFragment.newInstance(1, 1);
        } else if (position == 2) {
            return LocationFragment.newInstance(1, 2);
        } else {
            return LocationFragment.newInstance(1, 3);
        }
    }

    // Following method sets the tabs titles

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    // Following method is used to set the number of tabs

    @Override
    public int getCount() {
        return 4;
    }
}
