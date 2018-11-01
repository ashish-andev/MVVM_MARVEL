package com.marvel.app.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.marvel.app.MarvelApplication;

public class AppPreferencesHelper {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String PREF_NAME = "pref_marvel";

    private final SharedPreferences mPrefs;

    public AppPreferencesHelper() {
        mPrefs = MarvelApplication.getInstance().getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getAccessToken() {
        return mPrefs.getString(ACCESS_TOKEN, null);
    }

    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(ACCESS_TOKEN, accessToken).apply();
    }

}
