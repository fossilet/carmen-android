package com.siwei.carmen;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wkd on 15-11-28.
 */
public class MainActivity extends FragmentActivity {

    private List<String> titleList;
    private List<Fragment> fragementList;
    private ViewPager mPager;
    private MyFragementPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.main);

        mPager = (ViewPager) findViewById(R.id.pager);

        titleList = new ArrayList<>();
        titleList.add("用卡建议");
        titleList.add("我的信用卡");
        titleList.add("设置");

        fragementList = new ArrayList<>();
        fragementList.add(new SuggestionFragment());
        fragementList.add(new CardsFragment());
        fragementList.add(new SettingFragment());

        mAdapter = new MyFragementPagerAdapter(getSupportFragmentManager(),fragementList,titleList);
        mPager.setAdapter(mAdapter);



    }




}
