package com.marvel.app;

import android.app.Application;

public class MarvelApplication extends Application {

    private static MarvelApplication mInstance;

    public static MarvelApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
