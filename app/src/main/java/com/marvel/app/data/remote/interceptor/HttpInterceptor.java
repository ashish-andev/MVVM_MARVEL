package com.marvel.app.data.remote.interceptor;

import android.text.TextUtils;
import android.util.Log;

import com.marvel.app.utils.NetworkUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        try {
            if (NetworkUtils.isNetworkConnected()) {
                Response response = chain.proceed(request);
                ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    String result = body.string().trim();
                    return response.newBuilder().body(ResponseBody
                            .create(MediaType.parse("application/json"), result))
                            .build();
                } else if (response.code() >= 500 && response.code() < 600) {
                    //Server error
                } else if (response.code() >= 400 && response.code() < 500) {
                    //invalid request
                }
            }
        } catch (SocketTimeoutException e) {
            throw e;
        }
        throw new IOException();
    }
}
