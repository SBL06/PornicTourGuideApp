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

public class ContentAdapter extends FragmentPagerAdapter {

    //Following variables are declared in order to pass them as arguments to the ContentFragment

    private String description;
    private String information;
    private int audioId;
    private String[] tabNames;

    //The constructor takes 5 parameters, and 3 of them are attributes from the chosen location

    public ContentAdapter(FragmentManager fm, String description, String information,
                          int audioid, String[] tabtitles) {
        super(fm);

        // Variables are initialized

        this.description = description;
        this.information = information;
        this.audioId = audioid;
        this.tabNames = tabtitles;
    }

    // Following method is overridden in order to set the content of each tab. The fragment under the
    // first tab should display the description of the location, and the fragment under the second tab
    // should display practical informations.

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return ContentFragment.newInstance(description, audioId, true);
        } else {
            return ContentFragment.newInstance(information, audioId, false);
        }
    }

    // Following method sets the tabs titles

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    // Following method is used to set the number of tabs

    @Override
    public int getCount() {
        return 2;

    }
}
