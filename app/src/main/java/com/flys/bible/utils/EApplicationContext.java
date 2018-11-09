package com.flys.bible.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by User on 23/10/2018.
 */

public class EApplicationContext extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public void attachBaseContext(Context base) {
        //MultiDex.install(base);
        super.attachBaseContext(base);
    }
    public static Context getContext() {
        return mContext;
    }
}

