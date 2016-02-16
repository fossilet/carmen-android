package com.siwei.carmen;

import android.app.Application;
import android.content.Context;

/**
 * Created by wkd on 15-12-13.
 */
public class CarmenApplication extends Application{

    private  static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }


    public static Context getContext(){
        return context;
    }
}

