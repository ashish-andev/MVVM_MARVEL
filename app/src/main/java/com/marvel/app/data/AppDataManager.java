package com.marvel.app.data;

import com.google.gson.JsonElement;
import com.marvel.app.data.local.prefs.AppPreferencesHelper;
import com.marvel.app.data.remote.ApiClient;
import com.marvel.app.utils.Constants;
import com.marvel.app.utils.Utils;

import java.util.Calendar;
import java.util.TimeZone;

import io.reactivex.Observable;

public class AppDataManager {

    private final AppPreferencesHelper mPreferencesHelper;
//    private final AppDbHelper appDbHelper;

    public AppDataManager() {
        mPreferencesHelper = new AppPreferencesHelper();
//        appDbHelper = AppDbHelper.getInstance(MarvelApplication.getInstance().getApplicationContext());
    }

    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    public Observable<JsonElement> getCharacters(int offset) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        long timeStamp = calendar.getTimeInMillis() / 1000L;
        String hash = Utils.md5(String.valueOf(timeStamp) + Constants.PRIVATE_KEY + Constants.PUBLIC_KEY);
        return ApiClient.getApiService().getCharacters(20, offset, String.valueOf(timeStamp), Constants.PUBLIC_KEY, hash);
    }
}
