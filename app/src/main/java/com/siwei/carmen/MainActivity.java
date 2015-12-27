package com.siwei.carmen;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wkd on 15-11-28.
 */
public class MainActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    LinearLayout llSuggestion;
    LinearLayout llCards;
    LinearLayout llAbout;

    FragmentManager fm ;
    Fragment fragmentSuggestion;
    private Fragment fragmentCards;
    Fragment fragmentAbout;

    private CarmenFragmentPagerAdapter mAdapter;
    private CarmenDatabaseHelper dbHelper;

    private static MainActivity mInstance;

    public static MainActivity getInstance() {
        return mInstance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.main);
        fm = getFragmentManager();
        fragmentSuggestion = new SuggestionFragment();
        fragmentCards = new CardsFragment();
        fragmentAbout = new SettingFragment();


        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.llContent, fragmentSuggestion);
        //ft.addToBackStack(null);
        ft.commit();

        llSuggestion = (LinearLayout) findViewById(R.id.llSuggestion);
        llSuggestion.setOnClickListener(this);
        llSuggestion.setOnTouchListener(this);
        llCards = (LinearLayout) findViewById(R.id.llCards);
        llCards.setOnClickListener(this);
        llCards.setOnTouchListener(this);
        llAbout = (LinearLayout) findViewById(R.id.llAbout);
        llAbout.setOnClickListener(this);
        llAbout.setOnTouchListener(this);


        //create database.
        dbHelper = new CarmenDatabaseHelper(this,this.getString(R.string.database_name),null,1);
        getDbHelper().getWritableDatabase();

        mInstance = this;

    }


    public CarmenDatabaseHelper getDbHelper() {
        return dbHelper;
    }

    @Override
    public void onClick(View v) {

        changeFragment(v);

    }

    private void changeFragment(View v) {
        switch (v.getId()){
            case R.id.llSuggestion:{
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.llContent,fragmentSuggestion);
                //transaction.addToBackStack(null);
                transaction.commit();
                break;


            }
            case R.id.llCards: {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.llContent, getFragmentCards());
                //transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
            case R.id.llAbout: {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.llContent,fragmentAbout);
                //transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            changeFragment(v);
        }
        return false;
    }

    public Fragment getFragmentCards() {
        return fragmentCards;
    }
}
