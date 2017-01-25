package com.alpha.postoffice.adapters;

import com.alpha.postoffice.fragments.PlaceholderFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by i24sm on 1/24/2017.
 */

public class MoviesSectionsPagerAdapter extends FragmentPagerAdapter {

    public MoviesSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top rated";
            case 1:
                return "Top Trending";
            case 2:
                return "Popular";
        }
        return null;
    }
}


