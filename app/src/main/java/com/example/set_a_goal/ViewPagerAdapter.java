package com.example.set_a_goal;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numbOfTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence titles[], int mNumbOfTabs) {
        super(fm);
        this.titles = titles;
        this.numbOfTabs = mNumbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            CompactCalendarTab compactCalendarTab = new CompactCalendarTab();
            return compactCalendarTab;
        } else {
            CompactCalendarTab compactCalendarTab = new CompactCalendarTab();
            return compactCalendarTab;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}