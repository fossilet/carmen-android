package com.siwei.carmen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wkd on 15-11-30.
 */
public class CarmenFragmentPagerAdapter extends FragmentPagerAdapter {

    CarmenFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    CarmenFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragementList, List<String> titleList){
        super(fm);
        this.fragments = fragementList;
        this.titles = titleList;
    }

    private  List<Fragment> fragments;
    private List<String> titles;



    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
