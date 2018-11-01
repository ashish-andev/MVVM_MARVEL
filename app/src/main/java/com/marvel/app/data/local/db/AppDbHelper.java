package com.marvel.app.data.local.db;

import android.content.Context;

public class AppDbHelper {

    private static AppDbHelper appDbHelper;

    private AppDbHelper(Context context) {

    }

    public static AppDbHelper getInstance(Context context) {
        if (appDbHelper == null) {
            appDbHelper = new AppDbHelper(context);

        }
        return appDbHelper;
    }
}
