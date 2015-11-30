package com.siwei.carmen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wkd on 15-11-30.
 */
public class MyFragementPagerAdapter extends FragmentPagerAdapter {

    MyFragementPagerAdapter(FragmentManager fm){
        super(fm);
    }

    MyFragementPagerAdapter(FragmentManager fm,List<Fragment> fragementList,List<String> titleList){
        super(fm);
        this.fragements = fragementList;
        this.titles = titleList;
    }

    private  List<Fragment> fragements;
    private List<String> titles;



    @Override
    public Fragment getItem(int position) {
        return this.fragements.get(position);
    }

    @Override
    public int getCount() {
        return fragements.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
