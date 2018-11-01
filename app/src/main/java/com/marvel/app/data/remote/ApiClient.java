package com.marvel.app.data.remote;

import com.marvel.app.BuildConfig;
import com.marvel.app.data.remote.interceptor.HttpInterceptor;
import com.marvel.app.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = initApiService().create(ApiService.class);
        }
        return apiService;
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS);

        httpClient.addInterceptor(new HttpInterceptor());
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            httpClient.interceptors().add(logging);
        }
        return httpClient.build();
    }

    private static Retrofit initApiService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getHttpClient())
                .build();
    }
}
